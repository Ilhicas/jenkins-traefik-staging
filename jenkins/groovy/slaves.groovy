import hudson.model.Node.Mode
import hudson.model.*
import hudson.slaves.*
import hudson.plugins.sshslaves.*
import hudson.plugins.sshslaves.verifiers.NonVerifyingKeyVerificationStrategy
import jenkins.model.Jenkins

String agentName = "Docker Launcher"
String agentDescription = "Docker Launcher Slave"
String agentHome = "/home/jenkins/agent/"
String agentExecutors = 4
String labels = "docker"

createSlave(agentName, agentDescription, agentHome, agentExecutors, labels)
dumpSlaveCreds(agentName)
createSlaveSSH(agentName+"ssh", agentDescription, agentHome, agentExecutors, labels, "ssh-for-slaves" , "jenkins-slave-ssh", 22)

def createSlave(String agentName, String agentDescription, String agentHome, String agentExecutors, String labels="")
{
    DumbSlave dumb = new DumbSlave(agentName,
            agentDescription,
            agentHome,
            agentExecutors,
            Mode.EXCLUSIVE,
            labels,
            new JNLPLauncher(),
            RetentionStrategy.INSTANCE)

    Jenkins.instance.addNode(dumb)
    println "Agent created with $agentExecutors executors and home '$agentHome'"
}

def createSlaveSSH(String agentName, String agentDescription, String agentHome, String agentExecutors, String labels="", String credentialsName, String host, int port)
{

String credentialsId = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
    com.cloudbees.plugins.credentials.Credentials.class).find{
     cred -> cred.id == credentialsName}.with{ cred -> cred.id};

    DumbSlave dumb = new DumbSlave(agentName,
            agentDescription,
            agentHome,
            agentExecutors,
            Mode.EXCLUSIVE,
            labels,
            new SSHLauncher(host,
                port,
                credentialsId,        // The credentials id to connect as
                "",  // Options passed to the java vm
                "",            // Path to the host jdk installation
                "",  // This will prefix the start slave command
                "", // This will suffix the start slave command
                null, // Launch timeout in seconds
                null,       // The number of times to retry connection if the SSH connection is refused
                null,       // The number of seconds to wait between retries
                new NonVerifyingKeyVerificationStrategy()),
            RetentionStrategy.INSTANCE)

    Jenkins.instance.addNode(dumb)
    println "SSH Agent created with $agentExecutors executors and home '$agentHome'"
}

def dumpSlaveCreds(String agentName)
{
    for (aSlave in hudson.model.Hudson.instance.slaves) { if( agentName == aSlave.name){ println aSlave.name + "," + aSlave.getComputer().getJnlpMac(); println aSlave.getLauncher(); println aSlave.getComputer();} }
}


