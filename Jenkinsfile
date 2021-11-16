pipeline {
	agent any
	environment { 
        registry = "ziedlazrak/devops-timesheet" 
        registryCredential = 'dockerhub'
        dockerImage = '' 
    }
	stages{
			stage('Clean Install'){
				steps{
					bat "mvn clean install"
				}				
			}
			stage('Test'){
				steps{
					bat "mvn test"
				}				
			}
            stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
            }
            stage('packaging'){
				steps{
                    bat "mvn package"
                  }
            }
			stage('Deploy'){
				steps{
					bat "mvn deploy"
				}				
			}
			stage('Building Image'){
				steps{
					script{
						dockerImage = docker.build registry + ":$BUILD_NUMBER"
					}
				}				
			}
			stage('Deploy Image'){
				steps{
					script{
						docker.withRegistry( '', registryCredential ) 
                        {dockerImage.push()}
					}
				}
			}	
		} 
}