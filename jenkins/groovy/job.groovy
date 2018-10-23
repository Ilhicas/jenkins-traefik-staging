import jenkins.model.*;
import hudson.plugins.git.*;
import hudson.model.*;
import org.jenkinsci.plugins.workflow.job.*;
import org.jenkinsci.plugins.workflow.cps.*;

/*
  Author: André Ilhicas dos Santos <andre.ilhicas@fiercely.pt>
  Company: Fiercely Lda <www.fiercely.pt>
  Creates a new pipeline job in Jenkins
*/
def parent = Jenkins.instance

def createJob(job, parent)
{
    def scm = new GitSCM(job.gitUrl)
    scm.branches = [new BranchSpec(job.branch)];
    scm.userRemoteConfigs = scm.createRepoList(job.gitUrl, null)


    def flowDefinition = new CpsScmFlowDefinition(scm, job.jenkinsfilePath)
    def jobWorklow = new WorkflowJob(parent, job.name)

    jobWorklow.definition = flowDefinition
    jobWorklow.description  = job.description

}

def configuration = [:]
configuration.name = "Python Responder Sample Job"
configuration.description = "Python Responder Sample Job"
configuration.gitUrl = "https://github.com/Ilhicas/jenkins-traefik-staging.git"
configuration.branch = "master"
configuration.jenkinsfilePath = "ci/Jenkinsfile"


createJob(configuration, parent)

parent.reload()