
// For some reason we end up with an old version of the groovy jar in the lib directory
// so it needs to be blatted, hopefully a future version of grails will stop this happening
// We have an alternative way of excluding the groovy jar, left this here as a future reference for scripting
//eventCreateWarStart = { warName, stagingDir ->
//	Ant.delete(file:"${stagingDir}/WEB-INF/lib/groovy-1.8.8.jar", verbose:true)
//}