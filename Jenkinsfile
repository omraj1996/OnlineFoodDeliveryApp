pipeline {
	agent any

	environment{
	    SONARSCANNER='sonarscanner'
	    SONARSERVER='sonarserver'
	}

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

		stage('Sonar Analysis'){
		    environment {
		        scannerHome = tool "${SONARSCANNER}"
		    }
        	steps{
        		withSonarQubeEnv("${SONARSERVER}"){
        		    bat '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=OnlineFoodDeliveryApp \
        		    -Dsonar.projectName=OnlineFoodDeliveryApp \
        		    -Dsonar.projectVersion=1.0 \
        		    -Dsonar.sources=src/main/java/ \
        		    -Dsonar.java.binaries=target/classes/'''
        		}
        	}
        }

		stage('Deploy') {
			steps {
			    bat "copy target\\Online-Food-Ordering-App-0.0.1-SNAPSHOT.war \"C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\Online-Food-Ordering-App-0.0.1-SNAPSHOT.war\""
			}
		}
	}
}