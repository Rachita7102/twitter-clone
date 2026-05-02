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

    stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                fingerprint: true
            }
    }

    stage('Build Docker Image') {
            steps {
                        script {
                            if (isUnix()) {
                                sh 'docker build -t twitter-app .'
                            } else {
                                bat 'docker build -t twitter-app .'
                            }
                        }
                    }
    }

    stage('Stop Old Container') {
                 steps {
                             script {
                                 if (isUnix()) {
                                      sh 'docker stop $(docker ps -q)'
                                      sh 'docker rm $(docker ps -aq)'
                                 } else {
                                      bat 'docker stop $(docker ps -q)'
                                      bat 'docker rm $(docker ps -aq)'
                                 }
                             }
                         }
        }

    stage('Run Container') {
             steps {
                         script {
                             if (isUnix()) {
                                  sh 'docker run -d -p 8081:8085 twitter-app'
                             } else {
                                  bat 'docker run -d -p 8081:8085 twitter-app'
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