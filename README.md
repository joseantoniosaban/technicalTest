# Example Spring Boot + Gradle + JPA + H2

This is an simple example about how to build an application with Spring Boot, Spring Data, H2, Gradle, Lombok, Swagger, etc.

This application uses a H2 Data Base, wich is a Data Base in memory.

##How to run it?

```
gradle bootRun
```

##How can I test it?

you have two different endpoints:
```bash
curl -X 'GET' \
  'http://127.0.0.1:8080/api/technicalTest/v1/technicalTest/price?date=2020-06-14-10.00.00&productId=35455&brandId=1' \
  -H 'accept: */*'
```

```bash
curl -X 'GET' \
  'http://127.0.0.1:8080/api/technicalTest/v1/technicalTest/priceStream?date=2020-06-14-10.00.00&productId=35455&brandId=1' \
  -H 'accept: */*'
```