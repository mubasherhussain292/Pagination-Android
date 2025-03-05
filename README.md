## About
This project demonstrates **Pagination** in an Android application, ensuring efficient data loading and smooth scrolling for large datasets. It integrates **Clean Architecture**, **Kotlin Flows**, **Room Database**, and **Navigation Component** to provide a structured and scalable solution.

## Description
This project implements **Pagination** with a focus on seamless remote-to-local data handling. It utilizes:
- **Paging 3 Library** for efficient data loading
- **Room Database** for caching remote data locally
- **Kotlin Flows** for reactive data streams
- **Navigation Component** for handling in-app navigation
- **Clean Architecture** to separate concerns and ensure maintainability

## Pagination Implementation in Android

### Features
✅ Uses Jetpack Paging 3 Library  
✅ Remote-to-Local Data with Room DB  
✅ Kotlin Coroutines & Flows  
✅ Navigation Component for seamless transitions  
✅ Clean Architecture for modular and scalable code  
✅ Dependency Injection with Hilt  

### Project Structure
```
com.example.paginationapp
│── data            # Data sources, repositories  
│── domain          # Use cases, business logic  
│── presentation    # UI layer (ViewModel, UI events, states)  
│── di             # Dependency Injection setup  
│── navigation     # Navigation Component setup  
│── utils          # Helper functions, extensions  
```

### Getting Started
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/your-repo.git
   ```
2. Open in **Android Studio**  
3. Run the project on an emulator or device  

### Contributing
Feel free to open issues or submit pull requests to improve this project.

