# API CP Piloting Pathfinder

This repository supports early experimentation, prototyping, and validation of API-based data sharing approaches to inform a scalable model for cross-CJS integration.

## Installation

If using a macOS then it is recommended to use [Homebrew](https://brew.sh/):
```
brew install gradle
```
Alternatively see Gradle Build Tool [install](https://gradle.org/install/) for alternative options

## 📘 OpenAPI Versioning Strategy

This project uses an automated GitHub Actions workflow to manage versioning and publishing of the OpenAPI specification to SwaggerHub.

### 📌 Version Types
This project follows [Semantic Versioning](https://semver.org) (SemVer) conventions.

We support two types of versions:

### 🔧 Draft Versions (on every merge to main or master)
* Triggered by: push to main or master 
* Version format: vX.Y.Z-&lt;short-sha&gt;, e.g. v0.2.1-a1b2c3d
  * If no release tag exists, defaults to v0.0.0-&lt;short-sha&gt;
* Purpose: Used for previewing or collaborating on in-progress API definitions. 
* Behaviour:
  * The OpenAPI info.version is set automatically. 
  * The spec is uploaded to SwaggerHub as:
    * visibility: public 
    * published: false

### 🚀 Release Versions (on GitHub release publish)

* Triggered by: Publishing a GitHub release via the UI 
* Git tag format: vX.Y.Z (e.g. v1.0.0)
* Version used: X.Y.Z (without the v)
* Purpose: Final, published version of the API for public consumption.
* Behaviour:
  * The OpenAPI info.version is set to the release tag version. 
  * The spec is uploaded to SwaggerHub as:
    * visibility: public 
    * published: true

### 🔒 Secrets Required

Ensure the following secrets are configured in your GitHub repository:

| Secret Name              | Description                                 |
|--------------------------|---------------------------------------------|
| `SWAGGERHUB_API_KEY`     | API token for authenticating with SwaggerHub |
| `APIHUB_ORGANISATION`    | SwaggerHub username or organisation name    |

### 📁 OpenAPI File Location

The OpenAPI spec file must be located in: `src/main/resources/openapi/*.openapi.yml`

Only the first file matching that pattern will be used.

## License

This project is licensed under the [MIT License](LICENSE).
