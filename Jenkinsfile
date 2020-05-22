String cront_string = "H(0-29)/15 * * * *"

properties ([
    pipelineTriggers([pollSCM(cront_string)])
])

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        
        stage('Compile Project') {
            steps {
                checkout scm
                echo "-=- compiling project -=-"
                sh "mvn clean compile"
            }
        }
        
        stage('Test') {
            parallel {
                stage('Unit tests') {
                    steps {
                        echo "-=- execute unit tests -=-"
                        sh "mvn test"
                        junit 'target/surefire-reports/*.xml'
                        step( [ $class: 'JacocoPublisher' ] )
                    }
                    post {
                        success {
                            echo "SUCCESS"
                        }
                        failure {
                            echo "FAILED"
                        }
                    }
                }
                stage('Mutation tests') {
                    steps {
                        echo "-=- execute mutation tests -=-"
                        sh "mvn org.pitest:pitest-maven:mutationCoverage"
                    }
                    post {
                        success {
                            echo "SUCCESS"
                            //send notification
                        }
                        failure {
                            echo "FAILED"
                           //send notification
                        }
                    }

                }
            }
        }

        
        stage('Package') {
            when {
                anyOf {
                    branch 'master';
                    branch 'dev';
                }
            }
            steps {
                echo "-=- packaging project -=-"
                sh "mvn package -DskipTests"
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
       
        stage('Build && push Docker Image to Nexus') {
            when {
                anyOf {
                    branch 'master';
                    branch 'dev';
                }
            }
            steps {
                sh "mvn docker:build -DpushImage -DpushImageTag=latest -Dmaven.test.skip=true"
            }
        }
        
        stage('Anchor tests vulnerabities'){
            steps {
                sh 'echo "localhost:8083/app-java:latest ${WORKSPACE}/Dockerfile" > anchore_images'
                anchore name: 'anchore_images'
            }
        }
        
        stage('Deploy for development') {
            when {
                anyOf {
                    branch 'master';
                    branch 'dev';
                }
            }
            steps {
                //ansible job test
                echo "-=- Ansible job for test env"
                ansibleTower async: false, credential: '', extraVars: '', importTowerLogs: false, importWorkflowChildLogs: false, inventory: '', jobTags: '', jobTemplate: 'Production', jobType: 'run', limit: '', removeColor: false, skipJobTags: '', templateType: 'workflow', throwExceptionWhenFail: false, towerCredentialsId: 'ansible', towerServer: 'ansible', verbose: false

            }
        }
    }
}
