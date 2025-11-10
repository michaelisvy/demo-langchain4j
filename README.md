# Demo Application for LangChain4J with Spring

## Setup
* When using OpenAI, setup your API key with this environment variable: OPENAI_API_KEY
* The OpenAI cost for running this project with the default model should be a few cents at most. 
* See [application.properties](src/main/resources/application.properties) for more details

## Calls to the ChatModel (simple call, parameters, entities)
* See [MovieService](src/main/java/com/spring/example_01_movie/MovieService.java)

## Use an Image as Input and ask questions about the image
* See [ImageService](src/main/java/com/spring/example_02_multimodal/ImageService.java) and [ImageServiceTest](src/test/java/com/spring/example_02_multimodal/ImageServiceTest.java)

## ChatMemory usage
* See [LangChain4JConfig](src/main/java/com/spring/config/LangChain4JConfig.java), [ChatMemoryService](src/main/java/com/spring/example_03_memory/ChatMemoryService.java) and [ChatMemoryServiceTest](src/test/java/com/spring/example_03_memory/ChatMemoryServiceTest.java)

## RAG: read data from a PDF or Excel file
* See [MusicService](src/main/java/com/spring/example_04_RAG/MusicService.java) and [MusicServiceTest](src/test/java/com/spring/example_04_RAG/MusicServiceTest.java)

## Using an in-memory Vector Database
* See [BookService](src/main/java/com/spring/example_05_vector/BookService.java) and [BookVectorStoreConfig](src/main/java/com/spring/example_05_vector/BookVectorStoreConfig.java)

## Using tools (function calling)
* See [Tools](src/main/java/com/spring/tools/Tools.java), [AssistantWithTools](src/main/java/com/spring/tools/AssistantWithTools.java)
and [AssistantWithToolsTest](src/test/java/com/spring/tools/AssistantWithToolsTest.java)
