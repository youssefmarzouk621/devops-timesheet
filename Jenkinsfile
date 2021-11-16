pipeline {
	agent any
	environment { 
        registry = "ipharmacy/devops-timesheet" 
        registryCredential = 'dockerhub'
        dockerImage = '' 
    }
	stages{
			stage('Clean Install'){
				steps{
					bat "mvn clean install"
				}				
			}
			           
            stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
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