# learn-java-ee
Repository holding my Java EE learning codes.

## Build Requirements

- [Maven](https://maven.apache.org/)
- [Sass](https://sass-lang.com/) in path
- JDK

## Build

Run:

```bash
mvn package
```

## Local Test Deployment

Configure pom.xml in root of the project. Modify ```project.properties``` with
your local Java EE server's parameters or just directly modify cargo plugin in
```project.build.plugins``` to make cargo working.

Then run:

```bash
mvn cargo:deployer-deploy
```

Or:

```bash
mvn cargo:deployer-redeploy
```