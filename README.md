#This is product Service.


###Step 1:  Configure Postgres Database using docker
```bash
$ docker run --name pr_postgres -p 5432:5432 -e POSTGRES_USER=pr_dbuser -e POSTGRES_DB=products_db -e POSTGRES_PASSWORD=pr_dbpass -d postgres
```

###Step 2: Build Product service using mvn

```bash
mvn clean install -DskipTests
```

###Step 3: Building the docker image from project

```bash
docker build -f Dockerfile -t 'product:1.0.0' .
```

Once the above process is completed, you can verify whether the docker image is built successfully with following command.

```bash
docker images
```

###Step 4: Run the product service

```bash
docker run -d --name product-service --link pr_postgres:postgres -p 8080:8080 product:1.0.0
```


