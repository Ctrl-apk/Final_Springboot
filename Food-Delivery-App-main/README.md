# 🍕 NEUEat - Food Delivery System Backend 🚀

> *Bringing food closer to your ❤️ one order at a time!*

A production-grade **Food Delivery System** built with **Spring Boot**, **React**, and **MongoDB**. 
Currently deployed on Heroku: [https://neu-eat.herokuapp.com/](https://neu-eat.herokuapp.com/)

---

## 🎯 What Can You Do?

This app lets you experience the **complete food delivery ecosystem** by playing one of three roles:

| Role | Superpowers 🦸 |
|------|----------------|
| 👤 **Customer** | Search restaurants, browse menus, place orders, rate & review |
| 🏪 **Restaurant** | Manage menu items, view incoming orders, update restaurant info |
| 🚗 **Driver** | Accept deliveries, track orders, complete pickups & dropoffs |

---

## 🏗️ Architecture Highlights ✨

### Backend Stack (Spring Boot)
```
├── 🎮 Controllers    - REST API endpoints (no business logic here!)
├── 🔧 Services       - Core business logic & orchestration
├── 📦 Repositories   - Data access layer (MongoDB)
├── 📋 Models         - Domain entities
├── ⚠️ Exception      - Global error handling
└── 🔐 Security       - Authentication & role-based access
```

### Database
- **MongoDB** for flexible document storage
- Smart indexing for fast searches
- Role-based data isolation (Customer/Driver/Restaurant)

### Frontend
- **React** single-page application
- Responsive UI for all devices
- Real-time order updates

---

## 📸 UI Showcase

### 🔐 Authentication
<img width="600" height="400" src="image/login.png" alt="Login Page"/>

<img width="600" height="400" src="image/register.png" alt="Register Page"/>

### 🛍️ Customer Experience
<img width="600" height="400" src="image/restaurantSearch.png" alt="Restaurant Search"/>

<img width="600" height="400" src="image/placeOrder.png" alt="Place Order"/>

### ⭐ Reviews & Ratings
<img width="600" height="400" src="image/customerAddComment.png" alt="Add Comments & Ratings"/>

### 🏪 Restaurant Management
<img width="600" height="400" src="image/restaurantInfo.png" alt="Edit Restaurant Info"/>

<img width="600" height="400" src="image/restaurantMenu.png" alt="Manage Menu"/>

### 🚗 Driver Operations
<img width="600" height="400" src="image/driverFinishOrder.png" alt="Complete Order"/>

---

## ✅ PRD Requirements Coverage

### 🔑 User Management
- ✅ User registration & login
- ✅ Role-based access control (Customer/Driver/Restaurant)
- ✅ Secure credential storage
- ✅ JWT token generation & validation

### 📡 REST APIs
- ✅ Full CRUD operations for all entities
- ✅ Advanced filtering & search
- ✅ Pagination & sorting support
- ✅ Multiple endpoints per role
- ✅ 50+ well-documented endpoints

### 🛡️ Security & Validation
- ✅ Global exception handling (@ControllerAdvice)
- ✅ Input validation & sanitization
- ✅ Role-based endpoint protection
- ✅ Secure data flow
- ✅ JWT authentication

### 📊 Advanced Features
- ✅ Complex MongoDB queries
- ✅ Multi-role entity relationships
- ✅ Order state management
- ✅ Search & filtering engine
- ✅ Pagination with metadata
- ✅ File upload capability
- ✅ Email notifications
- ✅ API rate limiting
- ✅ Analytics APIs
- ✅ Caching strategies

### 🏗️ Code Quality
- ✅ Clean layered architecture (Controller → Service → Repository)
- ✅ Service/Repository separation of concerns
- ✅ Meaningful naming conventions
- ✅ Modular component design
- ✅ Swagger/OpenAPI documentation
- ✅ Environment-based configuration

---

## 🚀 Quick Start

### Prerequisites
- Java 8+
- Node.js 12.18.3+
- Maven 3.6+
- MongoDB 4.0+

### Run Backend (Port 8080)
```bash
# Navigate to project root
cd Food-Delivery-App-main

# Build and install dependencies
mvn clean install -DskipTests

# Run Spring Boot application
mvn spring-boot:run
```

**Backend available at:** `http://localhost:8080`

**Swagger API Docs:** `http://localhost:8080/swagger-ui.html`

### Run Frontend (Port 3000)
```bash
# Navigate to frontend
cd front-end

# Install dependencies
npm install

# Start React development server
npm start
```

**Frontend available at:** `http://localhost:3000`

---

## 📚 Tech Stack

| Component | Technology |
|-----------|-----------|
| **Backend** | Spring Boot 2.3.4+ |
| **Frontend** | React 16.14.0+ |
| **Database** | MongoDB |
| **Authentication** | JWT (Role-based) |
| **API Style** | RESTful |
| **API Documentation** | Swagger/OpenAPI 3 |
| **Package Manager** | Maven / NPM |
| **Build Tool** | Maven |
| **Database Driver** | Spring Data MongoDB |

---

## ✨ Features Implemented

### 🔐 Authentication & User Management
- ✅ User registration for all roles (Customer/Restaurant/Driver)
- ✅ Secure login with JWT token generation
- ✅ Role-based access control (RBAC)
- ✅ Password update functionality
- ✅ Phone number updates
- ✅ Address management
- ✅ Secure credential storage

### 🏪 Restaurant Management
- ✅ Restaurant registration & profile management
- ✅ Add/remove dishes from menu
- ✅ Update restaurant information (name, location, cuisines)
- ✅ View restaurant menu with detailed items
- ✅ Search restaurants with advanced filters
- ✅ Paginated restaurant listing (10-100 items per page)
- ✅ Filter restaurants by city and cuisine tags
- ✅ View menu with dish details and pricing

### 🛒 Order Management
- ✅ Create new orders from customer
- ✅ View active orders for restaurants
- ✅ Order history tracking for all roles
- ✅ Fetch customer orders
- ✅ Fetch restaurant orders
- ✅ Fetch driver orders
- ✅ Order status handling (PENDING → ACCEPTED → READY → DELIVERED)
- ✅ Order cancellation support
- ✅ Driver assignment for orders

### 📊 Pagination & Sorting
- ✅ Pagination support on all list endpoints
- ✅ Configurable page size (default: 20)
- ✅ Sorting by multiple fields (name, date, price)
- ✅ Pageable responses with metadata (pageNumber, pageSize, totalPages, totalElements)
- ✅ First/Last page indicators

### 🔍 Search & Filtering
- ✅ Search restaurants by name
- ✅ Filter by city
- ✅ Filter by cuisine type
- ✅ Filter by price range
- ✅ Multiple criteria filtering
- ✅ Advanced MongoDB queries

### ⭐ Reviews & Ratings
- ✅ Customer can rate restaurants (1-5 stars)
- ✅ Add written reviews/comments
- ✅ View all restaurant reviews
- ✅ Average rating calculation
- ✅ Review moderation support

### 📋 API Documentation
- ✅ Swagger UI available for testing
- ✅ Auto-generated API documentation
- ✅ OpenAPI 3.0 specification
- ✅ Request/response examples
- ✅ Error code documentation

### 🛡️ Exception Handling
- ✅ Centralized global exception handling using `@ControllerAdvice`
- ✅ Custom exception classes (ResourceNotFoundException, etc.)
- ✅ Consistent API error response format
- ✅ Meaningful error messages
- ✅ Proper HTTP status codes
- ✅ Stack trace for debugging

### ✔️ Input Validation
- ✅ Email format validation
- ✅ Phone number validation
- ✅ Required field validation
- ✅ String length constraints
- ✅ Custom validation annotations
- ✅ Validation error responses

### 🎯 Advanced MongoDB Features
- ✅ Complex aggregation queries
- ✅ Text search indexing
- ✅ Geospatial queries (location-based search)
- ✅ Complex filtering with multiple conditions
- ✅ Performance optimization with indexing

---

## 🔌 API Endpoints Overview

### Authentication
```
POST   /api/restaurants/register        # Restaurant signup
POST   /api/restaurants/login           # Restaurant login
POST   /api/customers/register          # Customer signup
POST   /api/customers/login             # Customer login
POST   /api/drivers/register            # Driver signup
POST   /api/drivers/login               # Driver login
```

### Restaurant APIs
```
GET    /api/restaurants                 # List all restaurants (with pagination)
GET    /api/restaurants/{id}            # Get restaurant details
PUT    /api/restaurants/{id}            # Update restaurant info
GET    /api/restaurants/{id}/menu       # Get menu items
POST   /api/restaurants/{id}/menu       # Add dish to menu
PUT    /api/restaurants/{id}/menu/{menuId}  # Update menu item
DELETE /api/restaurants/{id}/menu/{menuId}  # Remove menu item
GET    /api/restaurants/search?city=Boston  # Search with filters
GET    /api/restaurants/{id}/reviews    # Get reviews
```

### Order APIs
```
POST   /api/orders                      # Place new order
GET    /api/orders/{id}                 # Get order details
PUT    /api/orders/{id}/status          # Update order status
DELETE /api/orders/{id}                 # Cancel order
GET    /api/orders/customer/{customerId}  # Customer's orders
GET    /api/orders/restaurant/{restaurantId}  # Restaurant's orders
GET    /api/orders/driver/{driverId}    # Driver's deliveries
```

### Customer APIs
```
GET    /api/customers/{id}              # Get customer profile
PUT    /api/customers/{id}              # Update customer profile
POST   /api/customers/{id}/reviews      # Add review & rating
GET    /api/customers/{id}/cart         # View shopping cart
POST   /api/customers/{id}/cart         # Add to cart
```

### Analytics APIs
```
GET    /api/analytics/revenue           # Revenue reports
GET    /api/analytics/orders            # Order statistics
GET    /api/analytics/popular-items     # Popular dishes
GET    /api/analytics/delivery-time     # Delivery time analytics
```

All endpoints support **pagination**, **filtering**, and **sorting** through query parameters.

---

## 🧪 Testing

### Run Backend Tests
```bash
mvn test
```

### Run Frontend Tests
```bash
cd front-end
npm test
```

---

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📋 Project Details

### 🎓 CS5500 Final Term Project

This project is a comprehensive demonstration of:
- Backend system design best practices
- Spring Boot fundamentals & advanced features
- Secure REST API development
- Database modeling with MongoDB
- Clean code architecture
- Production-ready patterns
- Multi-tier application development

**Built with ❤️ by students learning real-world backend engineering**

### Project Type
- **Individual/Group Project** (1-5 members)
- **Technology Stack**: Spring Boot + React + MongoDB
- **Submission Method**: GitHub + Demo Video

### Learning Outcomes
- ✅ System design and scalability
- ✅ Security best practices
- ✅ Database optimization
- ✅ API design patterns
- ✅ Code organization
- ✅ Testing strategies

---

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

<div align="center">

**⭐ If you find this project helpful, please give it a star! ⭐**

Made with ❤️ by the NEUEat Development Team

For detailed API documentation, visit: **[Swagger UI](http://localhost:8080/swagger-ui.html)**

</div>

