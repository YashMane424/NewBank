# NewBank - AI Copilot Instructions

## Project Overview
**NewBank** is a Spring Boot 4.0.0-SNAPSHOT application (Java 17) that implements a banking system with security, JPA persistence, and MongoDB support.

## Architecture & Key Components

### Technology Stack
- **Framework**: Spring Boot 4.0 (snapshot) with Spring Web MVC
- **Database**: Dual-database support - PostgreSQL (primary), H2 (runtime), MongoDB (document storage)
- **ORM**: Spring Data JPA with Lombok annotations
- **Security**: Spring Security with form-based authentication
- **Validation**: Spring's built-in validation framework
- **Build**: Maven with Java 17 as the target version

### Package Structure
```
com.project.NewBank/
├── NewBankApplication.java          # Entry point, @SpringBootApplication
└── config/
    └── SecurityConfig.java          # Spring Security configuration bean
```

## Developer Workflows

### Building
```powershell
./mvnw clean install              # Full build with tests
./mvnw clean compile              # Compilation only
./mvnw clean build -DskipTests    # Skip tests during build
```

### Running the Application
```powershell
./mvnw spring-boot:run            # Run via Maven plugin
```

### Testing
```powershell
./mvnw test                        # Run all tests (JUnit 5)
./mvnw test -Dtest=<TestClassName> # Single test class
```

## Key Project Patterns & Conventions

### Security Configuration
SecurityConfig uses `@Configuration` + `@Bean` pattern for Spring Security customization:
- Public endpoints: `/`, `/home` (permit all)
- All other endpoints require authentication
- Form-based login enabled
- Reference: `config/SecurityConfig.java`

**Note**: Deprecated method warnings - this uses older Spring Security API. Modern approach requires `SecurityFilterChain` with newer request matchers.

### Dependency Injection
- Use `@Bean` methods in `@Configuration` classes for bean definitions
- Lombok annotations (`@Data`, `@Getter`, etc.) minimize boilerplate
- Constructor injection preferred over field injection

### Testing
- Uses JUnit 5 with `@SpringBootTest` for integration tests
- Test dependencies include database-specific starters (MongoDB, JPA, Security tests)
- Located in `src/test/java/` mirroring main package structure

## Critical Integration Points

### Data Persistence
- **PostgreSQL**: Primary JDBC database (runtime scope)
- **MongoDB**: Document-oriented NoSQL support via Spring Data MongoDB
- **H2**: In-memory/file database for development/testing (runtime scope)
- Configure active database in `application.properties` using `spring.datasource.url` and MongoDB connection strings

### Spring Security Integration
When adding new endpoints or controllers:
1. Define authorization rules in `SecurityConfig.filterChain()` using `antMatchers()` patterns
2. Add `@EnableWebSecurity` if modifying config
3. Remember deprecated APIs - refactor to `requestMatchers()` when updating

## File Conventions
- Maven wrapper scripts: `mvnw` (Linux/Mac), `mvnw.cmd` (Windows)
- Application properties: `src/main/resources/application.properties` (minimal - extend with profiles)
- Static web assets: `src/main/resources/static/`
- Templates: `src/main/resources/templates/` (if using server-side templating)

## Important Notes for Contributors
- **Java version**: Must target Java 17 - no Java 11/8 compatibility assumed
- **Lombok processing**: Configured in maven-compiler-plugin, ensure IDE has Lombok support enabled
- **Spring Boot 4.0**: This is a snapshot version - expect potential API changes before release
- **Dual-database design**: Consider whether repositories should use JPA entities or MongoDB documents

## Next Steps When Starting
1. Review `SecurityConfig.java` to understand authentication/authorization requirements
2. Run `./mvnw spring-boot:run` to verify setup
3. Check `application.properties` to configure database connections for your environment
4. Create domain entities (currently missing) in `src/main/java/com/project/NewBank/` directory
