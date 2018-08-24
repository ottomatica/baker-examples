# Baker Script: Basic Guide

Baker script is the recipe for your virtual machine. The `baker.yml` file defines the configuration of your baker environment, for example the ammount of RAM you want to have in your virtual machine; as well as the tools, services, and languages you want installed in the environment.

## Simple Baker file

To get started with Baker lets write a simple Baker file that creates a VM and give it an IP address:

``` yaml
# baker.yml
---
name: simple-baker-example
vm:
  ip: 192.168.33.33
```

Next, to Bake the virtual machine called `simple-baker-example`, we use `bake` command and specify the directory of our `baker.yml` file. (In this case running the command in the same directory):
``` shell
baker bake --local .
```
When Baker finishes Baking your VM, you can SSH to it by using the `ssh` command:
``` shell
baker ssh simple-baker-example
```

## Bakelets
Bakelets allow us to define an advanced computing environments in just a few lines of code. For example, in the `baker.yml` below we create a VM that has `java`, `maven`, and headless `chrome` installed. This is perfect for a simple selenium testing server. And we can do this in only 12 lines!
``` yaml
---
name: headless-chrome-vm
vm:
  ip: 192.168.33.34
lang:
  - java8
tools:
  - maven
packages:
  - apt:
    - google-chrome-stable:
        deb: https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
```

Some bakelets available are listed below:

- **lang**:
    - nodejs9
    - python2
    - java8
    - R
- **services**:
    - docker
    - mysql5.7
- **tools**:
    - ansible
    - jupyter
    - maven
- **packages**:
    - apt
- **config**:
    - template

``` yaml 
# baker.yml example of using all bakerlets
--- 
name: bakelets-format
vm:
    ip: 192.168.33.35
lang:
    - java8
    - nodejs9
    - python2
    - R:
        packages:
            - tidyverse
            - csv

services:
   - docker
   - mysql:
        version: 5.7
        service_conf: env/templates/mysql.cfg
        client_conf: env/templates/my.cnf

tools:
    - ansible
    - jupyter
    - maven
keys: 
  - another-baker-vm # This would place another-baker-vm's private key in /keys/another-baker-vm.id_rsa
packages:
  - apt:
    - git
    - build-essential
    - google-chrome-stable:
        deb: https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
```