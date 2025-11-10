package com.spring.example_05_vector;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Configuration
class BookVectorStoreConfig {

    public static final int NUMBER_OF_TOKENS_PER_CHUNK = 350;
    public static final int CHUNK_OVERLAP = 50;
    
    @Value("classpath:example_05_vector/murderer-in-paris.txt")
    private Resource bookContent;

    @Value("${langchain4j.open-ai.chat-model.model-name}")
    private String modelName;

    private static final Logger logger = LoggerFactory.getLogger(BookVectorStoreConfig.class);

    @Bean
    EmbeddingStore<TextSegment> bookEmbeddingStore(EmbeddingModel embeddingModel) throws IOException {
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        
        logger.info("Loading and processing book content...");
        
        // Load the document
        Path filePath = bookContent.getFile().toPath();
        Document document = FileSystemDocumentLoader.loadDocument(filePath, new TextDocumentParser());
        
        // Split into chunks
        DocumentSplitter splitter = DocumentSplitters.recursive(
            NUMBER_OF_TOKENS_PER_CHUNK,
            CHUNK_OVERLAP
        );
        List<TextSegment> segments = splitter.split(document);
        
        logger.info("Document split into {} segments", segments.size());
        
        // Generate embeddings and store them
        for (TextSegment segment : segments) {
            Embedding embedding = embeddingModel.embed(segment).content();
            embeddingStore.add(embedding, segment);
        }
        
        logger.info("Vectors have been generated and stored using model: {}", this.modelName);
        
        return embeddingStore;
    }
}
