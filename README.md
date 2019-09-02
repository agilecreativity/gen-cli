## gen-cli

[![Clojars Project](https://img.shields.io/clojars/v/gen-cli/lein-template.svg)](https://clojars.org/gen-cli/lein-template)
[![Dependencies Status](https://jarkeeper.com/agilecreativity/gen-cli/status.png)](https://jarkeeper.com/agilecreativity/gen-cli)

A Leiningen template for generate the basic Clojure/Java project template.

### Basic Usage

- For project that you like to include Java for interops 

```shell
# To generate the CLI with Java code for JVM interops
lein new gen-cli jvm-cli +java && cd jvm-cli && lein deps :tree && lein run
```

You will get the following structure :

```
jvm-cli
├── CHANGELOG.md
├── LICENSE
├── README.org
├── project.clj
└── src
    ├── main
    │   ├── clojure
    │   │   └── jvm_cli
    │   │       └── core.clj
    │   ├── java
    │   │   └── jvm_cli
    │   │       └── Demo.java
    │   └── resources
    └── test
        ├── clojure
        │   └── jvm_cli
        │       └── core_test.clj
        ├── java
        │   └── jvm_cli
        │       └── DemoTest.java
        └── resources
```

- For simple project (without +java option)

```shell
# To generate the new CLI without Java interops
lein new gen-cli simple-cli && cd simple-cli && lein deps :tree && lein run
```

You will get the following structure :

```
simple-cli
├── CHANGELOG.md
├── LICENSE
├── README.org
├── project.clj
└── src
    ├── main
    │   ├── clojure
    │   │   └── simple_cli
    │   │       └── core.clj
    │   └── resources
    └── test
        └── clojure
            └── simple_cli
                └── core_test.clj
```

For both type of projects, you will already have the initial git commit for the project.
It is ready for you to push to Github when you are ready to work on by you/your team.

*Pro Tips*

You can do this very quickly if you use [gh-utils](https://github.com/agilecreativity/gh-utils)
which allow you to create your git repository on Github from the command line.

### Links

- [Leiningen Mixed Projects](https://github.com/technomancy/leiningen/blob/stable/doc/MIXED_PROJECTS.md) template

### License

Copyright © 2019 Burin Choomnuan

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
