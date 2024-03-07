Question2:
code:
public class secondquestion implements schedulable{
    public void execute(SchedulableContext sc)
    {
        Date dt=system.today().addMonths(-6);
        List<Opportunity>oldrecords=[select Id,Name,StageName,RecordType.Name from Opportunity where StageName='Closed Won' and RecordType.name='New Deal' and CloseDate=:dt];
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

}
Anonymous window:
  String cronExp = '0 0 0 1 * ?';
System.schedule('inserting the values', cronExp, new secondquestion());
