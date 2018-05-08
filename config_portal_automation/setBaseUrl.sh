echo "set ui.base.url to $1"

command="sed -i.bak 's|^\(ui.base.url=\).*|\1$1|' src/test/resources/config.properties"

eval ${command}

