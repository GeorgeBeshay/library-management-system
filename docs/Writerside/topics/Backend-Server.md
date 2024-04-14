# Backend Server Architecture

The Maids.cc Library Management System adopts a layered architecture design to ensure modularity, 
flexibility, and maintainability. This architectural style separates the system into distinct layers, 
each with its own responsibility and abstraction level. The architecture can be decomposed into 3 layers. Listing them
from the top most to the down most layer: Endpoints Layers, Business Layer, DAOs Layer, and finally the real DBMS layer.
The business logic layer, encapsulating the application's rules and workflows, ensuring independence from specific endpoints logic. 
Supporting these layers is the data access and modelling layer, responsible for data storage, retrieval, and 
manipulation, abstracting away the complexities of data management. 
This layered approach promotes separation of concerns, enabling easier testing, scalability, and future extensibility of
the system's components.

The class diagram can be found [here](https://github.com/GeorgeBeshay/library-management-system/blob/enhancements/Design/Class%20Diagram.pdf).