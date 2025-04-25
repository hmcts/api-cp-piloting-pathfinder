# API CP Piloting Pathfinder

This repository supports early experimentation, prototyping, and validation of API-based data sharing approaches to inform a scalable model for cross-CJS integration.

## 🚀 Installation

To get started with this project, you'll need Java and Gradle installed.

### Prerequisites

- ☕️ **Java 21 or later**: Ensure Java is installed and available on your `PATH`.
- ⚙️ **Gradle**: You can install Gradle using your preferred method:

  **macOS (Recommended with Homebrew):**
  ```bash
  brew install gradle
  ```

  **Other Platforms:**
  Visit the [Gradle installation guide](https://gradle.org/install/) for platform-specific instructions.

You can verify installation with:
```bash
java -version
gradle -v
```

## 📘 OpenAPI Versioning Strategy

➡️ See [docs/openapi-versioning.md](docs/openapi-versioning.md) for full details on how versioning and publishing works.

## Artefact Versioning

> **ℹ️ Note on GitHub Packages:**  
> Draft versions (e.g., `vX.Y.Z-shortsha`) are treated as _pre-releases_ by GitHub Packages and will not be marked as the "latest" version automatically. Only clean releases (`vX.Y.Z`) are considered latest.

For example:

| Version         | GitHub Packages Sorting Priority                                 |
|----------------|------------------------------------------------------------------|
| `v0.0.8`        | ✅ (higher priority)                                              |
| `v0.0.8-792745e`| 🔽 (lower priority, treated like a beta/preview build)           |
| `v0.0.7`        | 🔽 (still lower if the numeric part is smaller)                  |

## License

This project is licensed under the [MIT License](LICENSE).
