# Demo Application for LangChain4J with Spring

## Setup
* When using OpenAI, setup your API key with this environment variable: OPENAI_API_KEY
* The OpenAI cost for running this project with the default model should be a few cents at most. 
* See [application.properties](src/main/resources/application.properties) for more details

## Calls to the ChatClient (simple call, parameters, entities)
* See [BookRecommendataionService](src/main/java/com/spring/book/BookRecommendationService.java) and [BookRecommendataionServiceTest](src/test/java/com/spring/book/BookRecommendationServiceTest.java)


## Use an Image as Input and ask questions about the image
* See [ImageService](src/main/java/com/spring/example_02_multimodal/ImageService.java) and [ImageServiceTest](src/test/java/com/spring/example_02_multimodal/ImageServiceTest.java)

## Using an in-memory Vector Database
* See [RagService](src/main/java/com/spring/rag/RagService.java) and [RagServiceTest](src/test/java/com/spring/rag/RagServiceTest.java)



