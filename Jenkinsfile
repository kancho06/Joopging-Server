pipeline {
    agent any
    environment {
            AWS_ACCESS_KEY_ID     = credentials('jenkins-aws-secret-key-id')
            AWS_SECRET_ACCESS_KEY = credentials('jenkins-aws-secret-access-key')
        }
    stages {
        // Jar 파일로 빌드 (테스트 부분 스킵..)
        stage('Build Jar') {
            steps {
                echo 'build'
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }
        // zip 파일 구성
        stage('zip') {
            steps{
                echo 'zip'
                sh 'cd /var/lib/jenkins/workspace/JoopgingServer/build/libs'
                sh 'cp -r /var/lib/jenkins/workspace/JoopgingServer/.ebextensions /var/lib/jenkins/workspace/JoopgingServer/build/libs/.ebextensions'
                sh 'cd /var/lib/jenkins/workspace/JoopgingServer/build/libs'
                sh 'zip -r ${JOB_NAME}.zip Joopging-0.0.1-SNAPSHOT.jar .ebextensions'
            }
        }
        // S3에 먼저 업로드 후 Deploy 진행
        stage('Upload S3') {
            steps {
                echo 'Uploading'
                sh 'aws s3 cp ${JOB_NAME}.zip s3://elasticbeanstalk-ap-northeast-2-168712278800/${JOB_NAME}-${GIT_BRANCH}-${BUILD_NUMBER}.zip \
                    --acl public-read-write \
                    --region ap-northeast-2' //서울리전
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying'
                sh 'aws elasticbeanstalk create-application-version \
                    --region ap-northeast-2 \
                    --application-name joopging-nodoker \
                    --version-label ${JOB_NAME}-${BUILD_NUMBER} \
                    --description ${BUILD_TAG} \
                    --source-bundle S3Bucket="elasticbeanstalk-ap-northeast-2-168712278800",S3Key="${JOB_NAME}-${GIT_BRANCH}-${BUILD_NUMBER}.zip"'
                sh 'aws elasticbeanstalk update-environment \
                    --region ap-northeast-2 \
                    --environment-name 	Joopgingnodoker-env \
                    --version-label ${JOB_NAME}-${BUILD_NUMBER}'
        }
    }
}
    post {
        always {
            echo '결과는...........'
        }
        // 성공 시 슬랙 #tickets 채널에 성공 메세지 보내기
        success {
            echo '성공!!!'
        }
        // 실패 시 슬랙 #tickets 채널에 성공 메세지 보내기
        failure {
            echo '실패..'
        }
        unstable {
            echo '실행이 불안정합니다!'
        }
        changed {
            echo '파이프 라인이 변경되었습니다!'
            echo '예를들어 이전에는 실패했지만 지금은 성공한 경우'
        }
    }
}