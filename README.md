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

![bookinghistory.jpeg](..%2F..%2FDownloads%2Fbookinghistory.jpeg)
![deskorroomscreen.jpeg](..%2F..%2FDownloads%2Fdeskorroomscreen.jpeg)
![slotscreen.jpeg](..%2F..%2FDownloads%2Fslotscreen.jpeg)
![mainscreen.jpeg](..%2F..%2FDownloads%2Fmainscreen.jpeg)
![createaccount.jpeg](..%2F..%2FDownloads%2Fcreateaccount.jpeg)
![login.jpeg](..%2F..%2FDownloads%2Flogin.jpeg)
