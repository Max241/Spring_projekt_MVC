pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                // Run Maven on a Unix agent.
                bat "mvn clean compile"
            }
        }
        stage('Test') {
                    steps {
                        // Run Maven on a Unix agent.aaa
                        bat "mvn test"
                    }
        }

    }
}
