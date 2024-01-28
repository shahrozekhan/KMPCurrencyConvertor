# Currency Conversion Application: Convert currencies accross different platforms! 💊🚀

Welcome to the Currency Conversion Application repository! This Compose Multiplatform currency conversion application is built upon the principles of a clean MVVM architecture. The application leverages the Restful webservice provided by [exchangeratesapi.io](http://api.exchangeratesapi.io).

## Key Features:

- ✅ **[Compose UI](https://developer.android.com/jetpack/compose):** Crafted with Jetpack Compose for a sleek and responsive user interface.
- ✅ **[Voyger](https://voyager.adriel.cafe/):** A multiplatform navigation library built for, and seamlessly integrated with, Jetpack Compose.
- ✅ **[Multiplatform](https://kotlinlang.org/docs/mpp-intro.html):** Ensures cross-platform compatibility and consistency.
- ✅ **[Moko Resources(Mvvm and Flow)](https://github.com/icerockdev/moko-resources):** Moko libraries are employed for implementing the MVVM architecture and handling data flow within the application.
- ✅ **[Koin](https://insert-koin.io/):** Efficient dependency injection across different platforms.
- ✅ **[SQLDelight](https://cashapp.github.io/sqldelight/2.0.1/multiplatform_sqlite/):** Efficient and maintainable data management.
- ✅ **[Kotlinx.datetime](https://kotlinlang.org/docs/datetime/):** Simplifies date and time-related functionalities.
- ✅ **[Material Design](https://material.io/design):** Adheres to Material Design principles for a visually appealing experience, supporting dyanmic Colors.
- ✅ **[kotlinx-coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** Asynchronous programming and managing background tasks.
- ✅ **[Ktor](https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html):** Network requests are managed by Ktor, enabling efficient and asynchronous communication with the API.

## Architecture:

The application follows a clean architecture with the following layers:
![MVVM Clean Architecture - Repository Pattern And Usecases.](https://miro.medium.com/v2/resize:fit:1400/format:webp/0*5eJUx2N-5IKoIJNO.png)

- ✅ **Data:** Manages data access, including network and database operations.
- ✅ **Domain:** Contains the business logic and domain-specific entities.
- ✅ **Presentation:** Handles UI-related logic and interacts with the ViewModel.

## Screenshots:

**Android Light Theme:**
![Android Light Mode](/android_l.webp)

**Android Dark Theme:**
![Android Dark Mode](/android_d.webp)

**iPhone Light:**
![iPhone Light](/iphone_l.webp)

**Android Dyanmic Colors(Light && Dark Theme):**
![Android Dyanmic Colors Dark Theme](/dyanmic_color_support.jpg)

## Next Target:

- The next milestone for the project is to create a Desktop version, extending the application's reach to additional platforms.
- Add Search in From and To Currencies.
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
