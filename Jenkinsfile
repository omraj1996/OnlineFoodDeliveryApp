pipeline {
	agent any

	tools {
      jdk 'JAVA_HOME'
    }

	stages {

		stage('Build'){
			steps {
				bat "mvn clean package"
			}
		}

		stage('Test'){
			steps{
				bat "mvn test"
			}
		}

		stage('Deploy') {
			steps {
			    bat "copy target\\Online-Food-Ordering-App-0.0.1-SNAPSHOT.war \"C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Online-Food-Ordering-App-0.0.1-SNAPSHOT.war""
			}
		}
	}
}