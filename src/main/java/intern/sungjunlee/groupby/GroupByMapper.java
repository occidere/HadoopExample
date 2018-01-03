package intern.sungjunlee.groupby;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GroupByMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private static final IntWritable ONE = new IntWritable(1);
	private Text word = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, 
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer st = new StringTokenizer(value.toString());
		
		/* id  time  D(U)  code  fields(csv) */
		String tkn, column[] = { "", "", "", "", "" };
		int i = 0;
		while(st.hasMoreTokens()) {
			tkn = st.nextToken();
			if(i<column.length) column[i++] = tkn;
		}
		
		// GROUP BY의 Key = '변경코드 csv필드들'
		String myKey = (column[3]+" "+column[4]).trim();
		
		// 공백이 아닌 데이터들에 대해서만 맵 적용
		if(myKey != null && myKey.length() > 0) {
			word.set(myKey);
			context.write(word, ONE);
		}
	}	
}