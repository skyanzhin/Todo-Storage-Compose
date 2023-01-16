enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("compose") {
            from(files("gradle/compose.versions.toml"))
        }
        create("androidx") {
            from(files("gradle/androidx.versions.toml"))
        }
    }
}

rootProject.name = "Todo Storage Compose"
include (":app")
