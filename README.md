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
Kotlin Language

Concise and expressive code for all application logic.
Enhanced safety with nullability features and type inference.
MVVM Architecture

Model: Represents the data layer, using Retrofit to fetch data and Room for local database storage.
View: Displays UI components (Activities, Fragments). Uses LiveData or StateFlow to observe data changes.
ViewModel: Contains the logic to interact with the Model and expose data to the View.
Retrofit Integration

API Interfaces: Written in Kotlin for defining RESTful API endpoints.
Coroutines Support: Ensures non-blocking, asynchronous operations for API calls.
Error Handling: Handles exceptions gracefully using try-catch blocks or Kotlin’s Result type.

Screens
![slotscreen](https://github.com/user-attachments/assets/2059cf8f-4bc5-4fb2-b78e-af144c5df34e)
![login](https://github.com/user-attachments/assets/ec6e54c0-e433-44f4-9478-897a6d192842)
![createaccount](https://github.com/user-attachments/assets/40551a7c-c325-443d-9968-4de9b6e372d8)
![mainscreen](https://github.com/user-attachments/assets/056e7fb8-49a9-4d6b-a97a-339ae41cf49f)
![deskorroomscreen](https://github.com/user-attachments/assets/0cb70199-1c19-4376-bbb6-d00ad1939c52)
![bookinghistory](https://github.com/user-attachments/assets/1d7dbe27-bf91-4f9f-8b5c-50b3fee1162e)

