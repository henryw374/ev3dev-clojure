uber:
			clj -T:build uber
deploy-uber:
			-Dclojure.server.repl="{:port 5555 :accept clojure.core.server/repl}"
ssh-robot:
			sshpass -p "maker" ssh robot@${ROBOT}
start-jvm:
			sshpass -p "maker" ssh robot@${ROBOT} java -jar lib1-standalone.jar
refresh-dns:
			sudo dscacheutil -flushcache; sudo killall -HUP mDNSResponder
copy-uber:
			sshpass -p "maker" scp target/lib1-standalone.jar robot@${ROBOT}:/home/robot/lib1-standalone.jar 
.PHONY: list
list:
		@$(MAKE) -pRrq -f $(lastword $(MAKEFILE_LIST)) : 2>/dev/null | awk -v RS= -F: '/^# File/,/^# Finished Make data base/ {if ($$1 !~ "^[#.]") {print $$1}}' | sort | egrep -v -e '^[^[:alnum:]]' -e '^$@$$' | xargs
