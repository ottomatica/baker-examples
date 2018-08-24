# Bake a Virtual Machine
#### Command: `baker bake {--repo | --local} -v`

Use `bake` command to bake a virtual machine. By default, baker will search the current directory for a `baker.yml`.

#### Options: 
- `--repo <Repository URL>`: to Bake a VM from remote repository that contains a [baker script](../bakerScript/basic.md) in it's root directory.
- `--local <Local Path>`: to Bake a VM from a local Baker script. Use a path that has a [baker script](../bakerScript/basic.md) in it's root directory.
- `-v`: Enable verbose output.