#This is product Service.


###Step 1:  Configure Postgres Database using docker
```bash
$ docker run --name pr_postgres --network my-net -p 5432:5432 -e POSTGRES_USER=pr_dbuser -e POSTGRES_DB=products_db -e POSTGRES_PASSWORD=pr_dbpass -d postgres
```
Note : if the container is already running :

```bash
$ docker start pr_postgres
```

To create products_db user and grant to pr_dbuser:

```bash
$ docker exec -it pr_postgres psql -d postgres -U pr_dbuser -c "CREATE DATABASE products_db;"
$ docker exec -it pr_postgres psql -d postgres -U pr_dbuser -c "GRANT ALL PRIVILEGES ON DATABASE products_db TO pr_dbuser;"
```


###Step 2: Build Product service using mvn

```bash
mvn clean install -DskipTests
```

###Step 3: Building the docker image from project

```bash
docker image build -f Dockerfile -t 'product:1.0.0' .
```

Once the above process is completed, you can verify whether the docker image is built successfully with following command.

```bash
docker image ls
```

###Step 4: Run the product service

```bash
docker run -d --name product --network my-net --link pr_postgres:postgres -p 8080:8080 product:1.0.0
```


