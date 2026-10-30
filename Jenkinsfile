pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    // Автозапуск: опрос GitHub каждые ~2 минуты (подходит, когда Jenkins на localhost)
    triggers {
        pollSCM('H/2 * * * *')
    }

    environment {
        STUDENT_NAME = 'Bakhtjan Apsatarov'
        IMAGE_NAME   = 'bakhtjan-apsatarov-student-api'
        IMAGE_TAG    = 'v1.0-bakhtjan-apsatarov'
        FULL_IMAGE   = "${IMAGE_NAME}:${IMAGE_TAG}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Pipeline started by student: ${STUDENT_NAME}"
                checkout scm
                sh 'git log -1 --pretty=format:"Commit: %h | %an | %s"'
            }
        }

        stage('Build') {
            steps {
                echo "[${STUDENT_NAME}] Building with Gradle..."
                sh 'chmod +x gradlew'
                sh './gradlew clean bootJar --no-daemon'
            }
        }

        stage('Test') {
            steps {
                echo "[${STUDENT_NAME}] Running automated tests..."
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                echo "[${STUDENT_NAME}] Building image ${FULL_IMAGE}"
                sh 'docker build -t ${FULL_IMAGE} .'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker image inspect ${FULL_IMAGE} --format "Image: {{index .RepoTags 0}} | Size: {{.Size}} bytes | Created: {{.Created}}"'
                sh 'docker images ${IMAGE_NAME}'
                echo "[${STUDENT_NAME}] VERIFY OK: image ${FULL_IMAGE} exists"
            }
        }
    }

    post {
        success { echo "BUILD SUCCESS for ${STUDENT_NAME}" }
        failure { echo "BUILD FAILED for ${STUDENT_NAME} - pipeline stopped" }
    }
}
