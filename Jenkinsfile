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
			    bat "mvn war:war deploy:deploy"
			}
		}
	}
}