# mkdocs-example

## Baker environment

``` yaml
name: mkdocs-example
container: 
  ports: 8005
lang:
  - python2
commands:
  build: mkdocs build
  serve: mkdocs serve -a 0.0.0.0:8005
```

## Try it out

Create with `baker bake`. To generate the site folder, run `baker run build`.

Then start a mkdocs server with `baker run serve`.

You can visit a live preview (see http://localhost:8005).
