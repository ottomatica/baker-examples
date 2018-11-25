# baker-vault

Experimental feature for storing encrypted files and decrypted in vm.

### Encrypting files with baker.

The secret.json file has been already been encrypted. To view decrypted contents, run: `baker vault -v secret.json`

When prompted, give the password: "baker". This will be cached locally, once entered.

To decrypt the file, run `baker vault -u secret.json`.

To encrypt again, run `baker vault secret.json`.

### Baker.yml

To copy encrypted files, you can use the vault bakelet.

Run `baker bake` in this directory.

```yaml
name: baker-vault
vm: 
  ip: 192.168.99.99
config:
  - vault:
    - file: secret.json
      dest: ~/secret.json
```

This will copy the unencrypted file to ~/secret.json in the VM. Confirm by running `baker ssh` and then `cat secret.json`.
