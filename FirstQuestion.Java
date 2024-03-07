Question1:
code:
public class firstquestion {
    public static Map<String, List<Sobject>>objectValues(List<Sobject>objects,String field_name)
    {
        Map<String,List<Sobject>> result=new Map<String,List<Sobject>>();
        for(Sobject s:objects)
        {
            if(s.get(field_name)!=null)
            {
                String field_value=String.valueof(s.get(field_name)==true);
                if(result.containsKey(field_value))
                {
                    result.get(field_value).add(s);   
                }
                else
                {
                    List<Sobject> obj=new List<Sobject>();
                    obj.add(s);
                    result.put(field_value,obj);
                }
            }
        }
        System.debug(result);
        return result;
    }

}
Anonymous Window:
List<Account> accounts = [SELECT Id,Industry from Account];
Map<String, List<SObject>> objectValues1 = firstquestion.objectValues(accounts, 'Industry');
