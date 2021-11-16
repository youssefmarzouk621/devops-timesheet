pipeline {
	agent any
	environment { 
        registry = "youssefmarzouk/devops-timesheet" 
        registryCredential = 'dockerhub'
        dockerImage = '' 
    }
    
	stages{
			stage('Clean Install'){
				steps{
					bat "mvn clean install -Dmaven.test.skip=true"
				}				
			}
			           
            stage('Sonar Analyse'){
				steps{
                    bat "mvn sonar:sonar"
                  }
            }
            
            stage('Test'){
				steps{
					bat "mvn test"
				}				
			}
            
			stage('Packaging'){
				steps{
					bat "mvn package -Dmaven.test.skip=true"
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