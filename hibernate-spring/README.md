# hibernate-spring

In this project, we demonstrate several advanced bakelets:

* `vars`     - values that will be available to all bakelets
* `prompt`   - asking for user input.
* `template` - copy parameterized files to destination 
* `mysql`    - service requiring configuration options

### Baker environment

The [baker environment](baker.yml/) sets up MySQL, Java 8, maven, prompts for user input, and copies parameterized templates into project.

``` yaml
name: hibernate-spring
vm:
  ip: 192.168.8.8
  ports: 8080
vars:
  - mysql_password:
      prompt: Type your password for mysql server
tools:
  - maven
services:
  - mysql:
      version: 8
      service_conf: env/templates/mysql.cfg
      client_conf: env/templates/my.cnf
lang:
  - java8
config:
  - template: 
      src: env/templates/hibernate-template.cfg.xml 
      dest: /Onboarding/CoffeeMaker/src/main/resources/hibernate.cfg.xml
commands:
  serve: cd CoffeeMaker && mvn spring-boot:run
  debug: cd CoffeeMaker && mvnDebug spring-boot:run
  test: cd CoffeeMaker && mvn test
```

### Project

A simple "CoffeeMaker" application serves up coffee ingredients and receipes via a REST endpoint (e.g. /api/v1/inventory). Visiting the site at http://192.168.8.8:8080, will allow you to interact with the web app. 

### Try it out

Create the vm.

``` bash
baker bake
```

Start up the web app. Then visit: http://192.168.8.8:8080

``` bash
baker run serve
```

Run unit tests

``` bash
baker run test
```

You should see the following results.

```
Results :

Tests run: 5, Failures: 0, Errors: 0, Skipped: 0

[INFO]
[INFO] --- jacoco-maven-plugin:0.7.5.201505241946:report (post-unit-test) @ CoffeeMaker ---
[INFO] Analyzed bundle 'CoffeeMaker' with 15 classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 40.738 s
[INFO] Finished at: 2018-08-20T00:51:23+00:00
[INFO] Final Memory: 24M/59M
[INFO] ------------------------------------------------------------------------
```