Question2:
code:
Batchclass
    code:
    public class Secondquestionbatch implements Database.Batchable<Sobject> {
 	Date dt= System.today().addMonths(-6);
    public Database.QueryLocator start(Database.BatchableContext bc) {
        
        return Database.getQueryLocator([select Id,Name,StageName,RecordType.Name from Opportunity where StageName='Closed Won' and RecordType.name='New Deal' and CloseDate=:dt]);
    }

    public void execute(Database.BatchableContext bc, List<Opportunity>oldrecords) {
        List<opportunity>newrecordsList=new List<Opportunity>();
        Id devRecordTypeId = Schema.SObjectType.Opportunity.getRecordTypeInfosByName().get('servicing').getRecordTypeId();
        for(Opportunity opp:oldrecords)
        {       
            Opportunity newrec=new Opportunity();
            newrec.Name=opp.Name;
            newrec.StageName='Prospecting';
            newrec.RecordTypeId=devRecordTypeId;
            newrec.CloseDate=System.Today().addDays(30);
            newrecordsList.add(newrec);
        }
        if(newrecordsList.size()>0)
        {
            insert newrecordsList;
        }

    }

    public void finish(Database.BatchableContext bc) {
        System.debug('Executed Successfully');
    }
}
Schedule class
code:
    public class secondquestion implements schedulable{
    public void execute(SchedulableContext sc)
    {
       Secondquestionbatch sec = new Secondquestionbatch();
        Database.executeBatch(sec); 
    }

}
Anonymous window:
  String cronExp = '0 0 0 1 * ?';
System.schedule('inserting the values', cronExp, new secondquestion());
