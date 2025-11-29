<div align="center">

# 📝 Code Snippet Manager API

### RESTful API para gestionar y compartir fragmentos de código

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-data-jpa)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI%203-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](./LICENSE)

**API REST completa** para crear, almacenar, buscar y gestionar snippets de código con soporte para múltiples lenguajes, etiquetas y búsqueda avanzada. Incluye documentación OpenAPI y endpoints de monitoreo con Spring Actuator.

[🚀 Quick Start](#-quick-start) • [📖 API Docs](#-api-endpoints) • [🔍 Búsqueda](#-búsqueda-avanzada) • [👤 Autor](#-autor)

---

</div>

## ✨ Características

<table>
<tr>
<td width="50%">

### 🔧 CRUD Completo
- **Crear** snippets con título, código, lenguaje y tags
- **Leer** snippets individuales o listar todos
- **Actualizar** snippets existentes
- **Eliminar** snippets por ID

</td>
<td width="50%">

### 🔍 Búsqueda Avanzada
- Buscar por **lenguaje de programación**
- Buscar por **etiquetas/tags**
- Filtrado flexible de resultados
- Respuestas paginables

</td>
</tr>
<tr>
<td width="50%">

### 📚 Documentación
- **Swagger UI** integrado
- **OpenAPI 3.0** specification
- Endpoints auto-documentados
- Ejemplos de request/response

</td>
<td width="50%">

### 📊 Monitoreo
- **Spring Actuator** habilitado
- Health checks
- Métricas de aplicación
- Info endpoints

</td>
</tr>
</table>

---

## 🏗️ Arquitectura

```
src/main/java/com/snippetapi/snippet_api/
├── controller/
│   └── SnippetController.java    # REST endpoints
├── service/
│   ├── SnippetService.java       # Interface de servicio
│   └── impl/
│       └── SnippetServiceImpl.java
├── repository/
│   └── SnippetRepository.java    # JPA Repository
├── model/
│   └── Snippet.java              # Entidad JPA
├── dto/
│   ├── SnippetDto.java           # Response DTO
│   ├── CreateSnippetRequest.java # Create request
│   └── UpdateSnippetRequest.java # Update request
├── exception/
│   ├── ResourceNotFoundException.java
│   └── GlobalExceptionHandler.java
└── SnippetApiApplication.java
```

---

## 🚀 Quick Start

### Prerrequisitos

- Java 21+
- Maven 3.8+

### Instalación

```bash
# Clonar el repositorio
git clone https://github.com/RodrigoSanchezDev/code-snippet-manager-api.git
cd code-snippet-manager-api

# Ejecutar
./mvnw spring-boot:run
```

### URLs Importantes

| Servicio | URL |
|----------|-----|
| 🌐 API Base | `http://localhost:8080/api/snippets` |
| 📖 Swagger UI | `http://localhost:8080/swagger-ui.html` |
| 📊 Actuator | `http://localhost:8080/actuator` |
| 🗄️ H2 Console | `http://localhost:8080/h2-console` |

---

## 📖 API Endpoints

### Snippets CRUD

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/api/snippets` | Crear nuevo snippet |
| `GET` | `/api/snippets` | Listar todos los snippets |
| `GET` | `/api/snippets/{id}` | Obtener snippet por ID |
| `PUT` | `/api/snippets/{id}` | Actualizar snippet |
| `DELETE` | `/api/snippets/{id}` | Eliminar snippet |
| `GET` | `/api/snippets/search` | Buscar por language o tag |

---

## 💻 Ejemplos de Uso

### Crear un Snippet

```bash
curl -X POST http://localhost:8080/api/snippets \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Hello World en Java",
    "code": "public class HelloWorld {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}",
    "description": "Programa básico de Hello World",
    "language": "java",
    "tags": ["beginner", "hello-world", "basics"]
  }'
```

### Respuesta

```json
{
  "id": 1,
  "title": "Hello World en Java",
  "code": "public class HelloWorld {...}",
  "description": "Programa básico de Hello World",
  "language": "java",
  "tags": ["beginner", "hello-world", "basics"]
}
```

---

## 🔍 Búsqueda Avanzada

### Buscar por Lenguaje

```bash
curl "http://localhost:8080/api/snippets/search?language=java"
```

### Buscar por Tag

```bash
curl "http://localhost:8080/api/snippets/search?tag=spring"
```

---

## 📋 Modelo de Datos

### Snippet Entity

```java
@Entity
@Table(name = "snippets")
public class Snippet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String code;
    
    private String description;
    
    @Column(nullable = false)
    private String language;
    
    @ElementCollection
    private Set<String> tags;
}
```

---

## 🛠️ Tech Stack

<div align="center">

<img src="https://skillicons.dev/icons?i=java,spring,maven,postgres&theme=dark" alt="Tech Stack"/>

</div>

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Spring Boot | 3.4.5 | Framework base |
| Spring Data JPA | 3.x | Persistencia ORM |
| Spring Validation | 3.x | Validación de DTOs |
| Springdoc OpenAPI | 2.x | Documentación API |
| H2 Database | 2.x | BD en memoria (dev) |
| PostgreSQL | 15+ | BD producción (opcional) |
| Lombok | 1.18 | Reducción de boilerplate |

---

## 📊 Actuator Endpoints

| Endpoint | Descripción |
|----------|-------------|
| `/actuator/health` | Estado de la aplicación |
| `/actuator/info` | Información del proyecto |
| `/actuator/metrics` | Métricas de rendimiento |

---

## 📈 Próximas Mejoras

- [ ] Autenticación JWT
- [ ] Paginación de resultados
- [ ] Versionado de snippets
- [ ] Syntax highlighting en respuestas
- [ ] Rate limiting
- [ ] Caché con Redis

---

## 👤 Autor

<div align="center">

**Rodrigo Sanchez**

[![Portfolio](https://img.shields.io/badge/Portfolio-sanchezdev.com-A855F7?style=for-the-badge&logo=googlechrome&logoColor=white)](https://sanchezdev.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-sanchezdev-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/sanchezdev)
[![GitHub](https://img.shields.io/badge/GitHub-RodrigoSanchezDev-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/RodrigoSanchezDev)

</div>

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---

<div align="center">

**⭐ Si este proyecto te fue útil, considera darle una estrella ⭐**

</div>
