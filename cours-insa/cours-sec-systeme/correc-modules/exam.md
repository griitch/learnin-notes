# declaration de types

type appli_t;
type appli_exec_t;
type appli_lib_t;
type appli_file_t;

# appli.fc

```
/usr/bin/appli      --      system_u:object_r:appli_exec_t;
/usr/lib64/appli/.*\.so     system_u:object_r:appli_lib_t;
/usr/appli/plugins/.*      system_u:object_r:appli_plugin_t;
HOME_DIR/\.appli(/.*\.txt)?      user_u:object_r:appli_file_t;
HOME_DIR/\.appli/plugins(/.*)?      user_u:object_r:appli_userplugin_t;
/root/\.appli(/.*)?         root:object_r:staff_appli_file_t;
```

# access control rules

```txt
# used by user and root
allow user_t appli_exec_t:file { execute getattr read}

allow _staff_t appli_exec_t:file { execute getattr read}

# admin installe plugins
allow _staff_t appli_plugin_t:dir { w_dir_perms }

```

# Transitions
