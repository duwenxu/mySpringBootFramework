pipeline {
	agent any

    tools{
         gradle 'gradle'
    }


	stages() {
		stage('checkout') {
			steps {
				checkout scm
			}
		}
		stage('build') {
			when {
				anyOf {
					branch 'develop'; branch 'fat'; branch 'uat'; branch 'pro'; branch 'fatapp'
				}
			}
			steps{
				sh 'gradle clean --refresh-dependencies build -x test'
			}
		}



		stage('pro') {
			when {
				branch 'pro'
			}
			steps {
				script {
					echo 'master build deploy'
					def customImage = docker.build('display/display:1.618')
					docker.withRegistry("http://harbor.xy.tc/","harbor_repository") {
    						customImage.push()
    				}
				}
			}
		}

        stage('develop') {
            when {
                branch 'develop'
            }
            steps {
                script {
                    docker.withServer('tcp://172.23.4.11:2375') {

                                    sh("docker rm -f display_dev || true")
                                    sh("docker rmi -f display_dev || true")
                                    docker.build('display_dev')
                                    sh("""
                                        docker run -d \
                                        --name display_dev \
                                        --restart=always \
                                        --privileged=true \
                                        --network macvlan0 \
                                        --memory=20g \
                                        --cpus=8 \
                                        --ip 172.23.0.140  \
                                        -v /etc/localtime:/etc/localtime:ro \
                                        -v /etc/resolv.conf:/etc/resolv.conf:ro \
                                        -v /home/display_uat/logs/:/logs \
                                        -e ENV=dev \
                                        display_dev
                                        """)
                    }
                }
            }
        }


		stage('uat') {
			when {
				branch 'uat'
			}
			steps {
				script {
					docker.withServer('tcp://172.23.4.14:2375') {

            						sh("docker rm -f display_uat || true")
            						sh("docker rmi -f display_uat || true")
            						docker.build('display_uat')
            						sh("""
            							docker run -d \
            							--name display_uat \
            							--restart=always \
            							--privileged=true \
            							--network macvlan0 \
            							--memory=20g \
            							--cpus=8 \
            							--ip 172.23.1.191  \
            							-v /etc/localtime:/etc/localtime:ro \
            							-v /etc/resolv.conf:/etc/resolv.conf:ro \
            							-v /home/display_uat/logs/:/logs \
            							-e ENV=uat \
            							display_uat
            							""")
					}
				}
			}
		}


		stage('fat') {
            when {
                branch 'fat'
            }
            steps {
                script {

                    docker.withServer('tcp://172.23.4.14:2375') {

                        sh("docker rm -f display_fat || true")
                        sh("docker rmi -f display_fat || true")
                        docker.build('display_fat')
                        sh("""
                            docker run -d \
                            --name display_fat \
                            --restart=always \
                            --privileged=true \
                            --network macvlan0 \
                            --memory=20g \
                            --cpus=8 \
                            --ip 172.23.5.191  \
                            -v /etc/localtime:/etc/localtime:ro \
                            -v /etc/resolv.conf:/etc/resolv.conf:ro \
                            -v /home/display_fat/logs/:/logs \
                            -e ENV=fat \
                            display_fat
                            """)
                    }
                }
            }
        }
	}

}