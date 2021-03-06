# Git Hub Repository List Application
Git Hub Repository List Application is a simple MVVM pattern application with Single Activity. This application represents simple git hub application with repository list and repository detail screens. 
## You can see detailed presentation pdf in "project presentation" folder.

<img src="/images/main-scene-empty-list.png" width="240"> | <img src="/images/main-scene-with-list.png" width="240"> | <img src="/images/detail-scene.png" width="240">

## Technologies

- Navigation Component: Consistent navigation between views
- ViewModel: Holds UI data across configuration changes
- LiveData: Lifecycle aware observable and data holder
- Retrofit: HTTP Client
- Gson: JSON serializer/deserializer
- Coroutines: Asynchronous programming
- Dagger: Dependency injection
- DataBinding: Binds UI components in layouts to data sources
- Glide: Image loading and caching

## Architecture

- Single Activity
- MVVM Pattern
- Clean Code

<img src="/images/architecture-diagram.png" width="320" height="460"> | <img src="/images/project-folder-structure.png" width="320" height="460">

## Unit Test

One of the benefits of using MVVM design pattern is unit testing. With single responsibility classes we can easily create unit test class. In the project, we have example unit test classes for viewmodel, usecase, repository etc. 

"mockK" library used for mocking data. 

## License

```
Copyright 2020 adesso Turkey

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
