# Demo Application for LangChain4J with Spring

## Setup
* When using OpenAI, setup your API key with this environment variable: OPENAI_API_KEY
* The OpenAI cost for running this project with the default model should be a few cents at most. 
* See [application.properties](src/main/resources/application.properties) for more details

## Calls to the LLM (simple call, parameters, entities)
* See [BookService](src/main/java/com/spring/book/BookService.java) and [BookServiceTest](src/test/java/com/spring/book/BookServiceTest.java)

## Use an Image as Input and ask questions about the image
* See [ImageService](src/main/java/com/spring/example_02_multimodal/ImageService.java) and [ImageServiceTest](src/test/java/com/spring/example_02_multimodal/ImageServiceTest.java)

## Using an in-memory Vector Database
* See [RagService](src/main/java/com/spring/rag/RagService.java) and [RagServiceTest](src/test/java/com/spring/rag/RagServiceTest.java)

## Using tools (function calling)
* See [Tools](src/main/java/com/spring/tools/Tools.java), [AssistantWithTools](src/main/java/com/spring/tools/AssistantWithTools.java)
and [AssistantWithToolsTest](src/test/java/com/spring/tools/AssistantWithToolsTest.java)
