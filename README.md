# Currency Conversion Application

Welcome to the Currency Conversion Application repository! This Compose Multiplatform currency conversion application is built upon the principles of a clean MVVM architecture. The application leverages the Restful webservice provided by [exchangeratesapi.io](http://api.exchangeratesapi.io).

## Key Features:

- **Compose Multiplatform:** The application utilizes the power of Kotlin Multiplatform and Compose for building a consistent UI across platforms.
- **Koin:** Dependency injection is handled by Koin, ensuring a modular and maintainable codebase.
- **Sql Delight:** Database operations are streamlined with Sql Delight, providing a seamless and type-safe database interaction.
- **Ktor:** Network requests are managed by Ktor, enabling efficient and asynchronous communication with the API.
- **Moko (Mvvm and Flow):** Moko libraries are employed for implementing the MVVM architecture and handling data flow within the application.
- **KotlinX Coroutines:** Asynchronous programming is achieved using Kotlin coroutines, ensuring a responsive user experience.

## Architecture:

The application follows a clean architecture with the following layers:

- **Data:** Manages data access, including network and database operations.
- **Domain:** Contains the business logic and domain-specific entities.
- **Presentation:** Handles UI-related logic and interacts with the ViewModel.

## Screenshots:

**Android Light Theme:**
![Android Light Mode](/android_l.webp)

**Android Dark Theme:**
![Android Dark Mode](/android_d.webp)

**iPhone Light:**
![iPhone Light](/iphone_l.webp)

## Next Target:

- The next milestone for the project is to create a Desktop version, extending the application's reach to additional platforms.
- Add Search in From and To Currencies.
- Add Splash screen (Navigation [Voyger](https://voyager.adriel.cafe/))
- Mark the currencies Favorite and Add them to database.
- Add SSO (Single Sign-On) feature using firebase (Save favorite currencies, show trends and graphs)
- Add Firebase analytics for Crash reports and Analytics.
- Documentation -> [Kdoc](https://kotlinlang.org/docs/kotlin-doc.html) -> dokka documentation.
- Code Coverage -> Add [jacoco plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html)

## Getting Started:

To explore the application and run it locally, follow the steps outlined in the [documentation](/docs/getting-started.md).

## Contributing:

If you're interested in contributing to the project, please refer to the [contribution guidelines](/CONTRIBUTING.md) for more details.

## License:

This project is licensed under the [MIT License](/LICENSE).

Thank you for checking out the Currency Conversion Application! Feel free to explore the codebase and provide feedback. Happy coding! 🚀
