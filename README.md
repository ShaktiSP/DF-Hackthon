# DF-Hackthon

# Coworking Space App

This is a mobile application developed as part of the DF Hackathon. The app helps users discover, book, and manage coworking spaces efficiently.

## Features

- **Booking System:** Reserve spaces for specific time slots with ease.
- **User-Friendly Interface:** Intuitive design and seamless user experience.
- **Real-Time Updates:** Dynamic data integration for the latest availability and pricing.

## Project Structure

DF-Hackthon/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── data/                  # Data layer (APIs, Room database, Repositories)
│   │   │   │   │   ├── api/               # Retrofit API interfaces
│   │   │   │   │   ├── model/             # Data models
│   │   │   │   │   └── repository/        # Repository classes for data handling
│   │   │   │   ├── ui/                    # UI layer (Activities, Fragments, Adapters)
│   │   │   │   │   ├── view/              # Views (UI components)
│   │   │   │   │   ├── viewmodel/         # ViewModels for managing UI logic
│   │   │   │   │   └── adapter/           # RecyclerView adapters
│   │   │   │   └── util/                  # Utility classes and helpers
│   │   │   ├── res/                       # Resource files (layouts, drawables, etc.)
│   │   │   └── AndroidManifest.xml        # App manifest file
├── build.gradle.kts                       # Project build configuration
├── gradle/                                # Gradle configuration files
├── gradle.properties                      # Gradle properties
├── gradlew                                # Gradle wrapper script (Linux/Unix)
├── gradlew.bat                            # Gradle wrapper script (Windows)
├── settings.gradle.kts                    # Gradle settings file
└── README.md                              # Project documentation

Key Components
MVVM Architecture

Model: Manages the app's data, using repositories to fetch and process data from APIs or local databases.
View: The UI layer (Activities, Fragments) observes data changes via LiveData and reflects updates on the screen.
ViewModel: Acts as a mediator between the View and Model, holding UI-related data and business logic.
Retrofit Integration

API Interfaces: Defines the endpoints for network requests.
Repository: Centralizes data management by fetching and caching data from the API using Retrofit.
Asynchronous Operations: Uses Kotlin Coroutines for efficient API calls.

Screens
![slotscreen](https://github.com/user-attachments/assets/2059cf8f-4bc5-4fb2-b78e-af144c5df34e)
![login](https://github.com/user-attachments/assets/ec6e54c0-e433-44f4-9478-897a6d192842)
![createaccount](https://github.com/user-attachments/assets/40551a7c-c325-443d-9968-4de9b6e372d8)
![mainscreen](https://github.com/user-attachments/assets/056e7fb8-49a9-4d6b-a97a-339ae41cf49f)
![deskorroomscreen](https://github.com/user-attachments/assets/0cb70199-1c19-4376-bbb6-d00ad1939c52)
![bookinghistory](https://github.com/user-attachments/assets/1d7dbe27-bf91-4f9f-8b5c-50b3fee1162e)

