# sew9-2324-worttrainer-jcejkaGit
sew9-2324-worttrainer-jcejkaGit created by GitHub Classroom
## Gradle Project
First clone the empty project in Intellij, then create a new module and have it built in gradle via Intellij selection.
## Word Trainer Model
I opened my old project in Eclipse and read the code again. I had to change a few functions so that they meet the current requirements of the project.
- Statistics
- Current word entry
- Collection to replace the word list class
## Project Overview

WordTrainer is a Java-based application designed to help users learn new words more effectively. It incorporates various features to facilitate the learning process, including word list management, progress tracking, and interactive training sessions. The application follows the Model-View-Controller (MVC) architectural pattern, ensuring a clear separation of concerns and enhancing maintainability.

## MVC Architecture

### Model

- `WortEintrag`: Represents a word entry, encapsulating details such as the word itself, its meaning, and usage examples. This class is central to the application's functionality, serving as the primary data structure for word storage and manipulation.

- `JsonPersistence`: Extends the `Persistence` class to provide functionality for storing and retrieving application data in JSON format. This class plays a crucial role in data persistence, enabling the application to maintain state across sessions.

### View

- `CustomImageDialog`: A custom dialog class used to display images. It enhances the user interface by allowing for more dynamic content presentation, particularly useful in making the learning process more engaging.

- `ImageLoader`: Responsible for loading and displaying images from URLs. This class is instrumental in fetching external resources, thereby enriching the user experience with visual aids.

### Controller

- `WortTrainer`: Serves as the central controller for the application. It manages interactions between the model and view components, orchestrating the overall flow of the application. This class is key to implementing the MVC architecture effectively.

### Main Entry Point

- `Main`: The main class that serves as the entry point for the application. It initializes the necessary components and starts the application.



