
## gen-cli

A Leiningen template for generate the basic Clojure/Java project template.

### Usage

```
git clone https://github.com/agilecreativity/gen-cli.git && cd gen-cli
lein install

# Then you can create new CLI skeletion quickly like
mkdir -p ~/tmp && cd ~/tmp

# Then generate the new CLI, and run it quickly
lein new gen-cli awesome-cli && cd awesome-cli && lein deps :tree && lein run

# To generate the CLI with with Java code for interops try
lein new gen-cli awesome-cli +java && cd awesome-cli && lein deps :tree && lein run
```

### Links
- [Leiningen Mixed Projects](https://github.com/technomancy/leiningen/blob/stable/doc/MIXED_PROJECTS.md) template

### License

Copyright © 2019 Burin Choomnuan

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
