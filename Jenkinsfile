pipeline {
agent any
stages {

    stage('Checkout') {
        steps {
            git branch: 'develop', url: 'https://github.com/Rachita7102/twitter-clone.git'
        }
    }

    stage('Build') {
        steps {
            script {
                if (isUnix()) {
                    sh 'mvn clean compile'
                } else {
                    bat 'mvn clean compile'
                }
            }
        }
    }

    stage('Test') {
        steps {
            script {
                if (isUnix()) {
                    sh 'mvn test'
                } else {
                    bat 'mvn test'
                }
            }
        }
    }

    stage('Package') {
        steps {
            script {
                if (isUnix()) {
                    sh 'mvn package'
                } else {
                    bat 'mvn package'
                }
            }
        }
    }
}

post {
    success {
        echo 'Build Successful 🎉'
    }
    failure {
        echo 'Build Failed ❌'
    }
}
}