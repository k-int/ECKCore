dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

// environment specific settings
environments {
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			driverClassName = "com.mysql.jdbc.Driver"
			username = "k-int"
			password = "k-int"
			url = "jdbc:mysql://localhost/eckCore"
			//&autoReconnect=true&characterEncoding=utf8"
		}
	}
	test {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			driverClassName = "com.mysql.jdbc.Driver"
			username = "k-int"
			password = "k-int"
			url = "jdbc:mysql://localhost/eckCore"
			//&autoReconnect=true&characterEncoding=utf8"
		}
	}
	production {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop','update'
			driverClassName = "com.mysql.jdbc.Driver"
			username = "k-int"
			password = "k-int"
			url = "jdbc:mysql://localhost/eckCore"
			//&autoReconnect=true&characterEncoding=utf8"
		}
	}
}
