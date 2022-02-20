// Generated from C:/Users/Joshua/Desktop/Files/Java/MicroScheme/src/main/antlr4/com/joshuacrotts/microscheme\MicroScheme.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MicroSchemeParser}.
 */
public interface MicroSchemeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#microScheme}.
	 * @param ctx the parse tree
	 */
	void enterMicroScheme(MicroSchemeParser.MicroSchemeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#microScheme}.
	 * @param ctx the parse tree
	 */
	void exitMicroScheme(MicroSchemeParser.MicroSchemeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(MicroSchemeParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(MicroSchemeParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#variableBody}.
	 * @param ctx the parse tree
	 */
	void enterVariableBody(MicroSchemeParser.VariableBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#variableBody}.
	 * @param ctx the parse tree
	 */
	void exitVariableBody(MicroSchemeParser.VariableBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterProcedureDeclaration(MicroSchemeParser.ProcedureDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitProcedureDeclaration(MicroSchemeParser.ProcedureDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#procedureParameters}.
	 * @param ctx the parse tree
	 */
	void enterProcedureParameters(MicroSchemeParser.ProcedureParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#procedureParameters}.
	 * @param ctx the parse tree
	 */
	void exitProcedureParameters(MicroSchemeParser.ProcedureParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#procedureBody}.
	 * @param ctx the parse tree
	 */
	void enterProcedureBody(MicroSchemeParser.ProcedureBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#procedureBody}.
	 * @param ctx the parse tree
	 */
	void exitProcedureBody(MicroSchemeParser.ProcedureBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#declExpr}.
	 * @param ctx the parse tree
	 */
	void enterDeclExpr(MicroSchemeParser.DeclExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#declExpr}.
	 * @param ctx the parse tree
	 */
	void exitDeclExpr(MicroSchemeParser.DeclExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(MicroSchemeParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(MicroSchemeParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#beginExpr}.
	 * @param ctx the parse tree
	 */
	void enterBeginExpr(MicroSchemeParser.BeginExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#beginExpr}.
	 * @param ctx the parse tree
	 */
	void exitBeginExpr(MicroSchemeParser.BeginExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#booleanExpr}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpr(MicroSchemeParser.BooleanExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#booleanExpr}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpr(MicroSchemeParser.BooleanExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#evalExpr}.
	 * @param ctx the parse tree
	 */
	void enterEvalExpr(MicroSchemeParser.EvalExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#evalExpr}.
	 * @param ctx the parse tree
	 */
	void exitEvalExpr(MicroSchemeParser.EvalExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#applyExpr}.
	 * @param ctx the parse tree
	 */
	void enterApplyExpr(MicroSchemeParser.ApplyExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#applyExpr}.
	 * @param ctx the parse tree
	 */
	void exitApplyExpr(MicroSchemeParser.ApplyExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#whenExpr}.
	 * @param ctx the parse tree
	 */
	void enterWhenExpr(MicroSchemeParser.WhenExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#whenExpr}.
	 * @param ctx the parse tree
	 */
	void exitWhenExpr(MicroSchemeParser.WhenExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#whenCond}.
	 * @param ctx the parse tree
	 */
	void enterWhenCond(MicroSchemeParser.WhenCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#whenCond}.
	 * @param ctx the parse tree
	 */
	void exitWhenCond(MicroSchemeParser.WhenCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#unlessExpr}.
	 * @param ctx the parse tree
	 */
	void enterUnlessExpr(MicroSchemeParser.UnlessExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#unlessExpr}.
	 * @param ctx the parse tree
	 */
	void exitUnlessExpr(MicroSchemeParser.UnlessExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#unlessCond}.
	 * @param ctx the parse tree
	 */
	void enterUnlessCond(MicroSchemeParser.UnlessCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#unlessCond}.
	 * @param ctx the parse tree
	 */
	void exitUnlessCond(MicroSchemeParser.UnlessCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#setExpr}.
	 * @param ctx the parse tree
	 */
	void enterSetExpr(MicroSchemeParser.SetExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#setExpr}.
	 * @param ctx the parse tree
	 */
	void exitSetExpr(MicroSchemeParser.SetExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#setListExpr}.
	 * @param ctx the parse tree
	 */
	void enterSetListExpr(MicroSchemeParser.SetListExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#setListExpr}.
	 * @param ctx the parse tree
	 */
	void exitSetListExpr(MicroSchemeParser.SetListExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#letExpr}.
	 * @param ctx the parse tree
	 */
	void enterLetExpr(MicroSchemeParser.LetExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#letExpr}.
	 * @param ctx the parse tree
	 */
	void exitLetExpr(MicroSchemeParser.LetExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#letStarExpr}.
	 * @param ctx the parse tree
	 */
	void enterLetStarExpr(MicroSchemeParser.LetStarExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#letStarExpr}.
	 * @param ctx the parse tree
	 */
	void exitLetStarExpr(MicroSchemeParser.LetStarExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#letRecExpr}.
	 * @param ctx the parse tree
	 */
	void enterLetRecExpr(MicroSchemeParser.LetRecExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#letRecExpr}.
	 * @param ctx the parse tree
	 */
	void exitLetRecExpr(MicroSchemeParser.LetRecExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#letParameters}.
	 * @param ctx the parse tree
	 */
	void enterLetParameters(MicroSchemeParser.LetParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#letParameters}.
	 * @param ctx the parse tree
	 */
	void exitLetParameters(MicroSchemeParser.LetParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#letBody}.
	 * @param ctx the parse tree
	 */
	void enterLetBody(MicroSchemeParser.LetBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#letBody}.
	 * @param ctx the parse tree
	 */
	void exitLetBody(MicroSchemeParser.LetBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#lambdaExpr}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpr(MicroSchemeParser.LambdaExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#lambdaExpr}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpr(MicroSchemeParser.LambdaExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#lambdaParameters}.
	 * @param ctx the parse tree
	 */
	void enterLambdaParameters(MicroSchemeParser.LambdaParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#lambdaParameters}.
	 * @param ctx the parse tree
	 */
	void exitLambdaParameters(MicroSchemeParser.LambdaParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#lambdaBody}.
	 * @param ctx the parse tree
	 */
	void enterLambdaBody(MicroSchemeParser.LambdaBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#lambdaBody}.
	 * @param ctx the parse tree
	 */
	void exitLambdaBody(MicroSchemeParser.LambdaBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#doExpr}.
	 * @param ctx the parse tree
	 */
	void enterDoExpr(MicroSchemeParser.DoExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#doExpr}.
	 * @param ctx the parse tree
	 */
	void exitDoExpr(MicroSchemeParser.DoExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#doDecl}.
	 * @param ctx the parse tree
	 */
	void enterDoDecl(MicroSchemeParser.DoDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#doDecl}.
	 * @param ctx the parse tree
	 */
	void exitDoDecl(MicroSchemeParser.DoDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#doTest}.
	 * @param ctx the parse tree
	 */
	void enterDoTest(MicroSchemeParser.DoTestContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#doTest}.
	 * @param ctx the parse tree
	 */
	void exitDoTest(MicroSchemeParser.DoTestContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#doTrueExpr}.
	 * @param ctx the parse tree
	 */
	void enterDoTrueExpr(MicroSchemeParser.DoTrueExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#doTrueExpr}.
	 * @param ctx the parse tree
	 */
	void exitDoTrueExpr(MicroSchemeParser.DoTrueExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#doBody}.
	 * @param ctx the parse tree
	 */
	void enterDoBody(MicroSchemeParser.DoBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#doBody}.
	 * @param ctx the parse tree
	 */
	void exitDoBody(MicroSchemeParser.DoBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#condExpr}.
	 * @param ctx the parse tree
	 */
	void enterCondExpr(MicroSchemeParser.CondExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#condExpr}.
	 * @param ctx the parse tree
	 */
	void exitCondExpr(MicroSchemeParser.CondExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#condForm}.
	 * @param ctx the parse tree
	 */
	void enterCondForm(MicroSchemeParser.CondFormContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#condForm}.
	 * @param ctx the parse tree
	 */
	void exitCondForm(MicroSchemeParser.CondFormContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#ifExpr}.
	 * @param ctx the parse tree
	 */
	void enterIfExpr(MicroSchemeParser.IfExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#ifExpr}.
	 * @param ctx the parse tree
	 */
	void exitIfExpr(MicroSchemeParser.IfExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#applicationExpr}.
	 * @param ctx the parse tree
	 */
	void enterApplicationExpr(MicroSchemeParser.ApplicationExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#applicationExpr}.
	 * @param ctx the parse tree
	 */
	void exitApplicationExpr(MicroSchemeParser.ApplicationExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#applicationArgs}.
	 * @param ctx the parse tree
	 */
	void enterApplicationArgs(MicroSchemeParser.ApplicationArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#applicationArgs}.
	 * @param ctx the parse tree
	 */
	void exitApplicationArgs(MicroSchemeParser.ApplicationArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#symbolExpr}.
	 * @param ctx the parse tree
	 */
	void enterSymbolExpr(MicroSchemeParser.SymbolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#symbolExpr}.
	 * @param ctx the parse tree
	 */
	void exitSymbolExpr(MicroSchemeParser.SymbolExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#symbolDatum}.
	 * @param ctx the parse tree
	 */
	void enterSymbolDatum(MicroSchemeParser.SymbolDatumContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#symbolDatum}.
	 * @param ctx the parse tree
	 */
	void exitSymbolDatum(MicroSchemeParser.SymbolDatumContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#symbolDatumRep}.
	 * @param ctx the parse tree
	 */
	void enterSymbolDatumRep(MicroSchemeParser.SymbolDatumRepContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#symbolDatumRep}.
	 * @param ctx the parse tree
	 */
	void exitSymbolDatumRep(MicroSchemeParser.SymbolDatumRepContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#quasiSymbolExpr}.
	 * @param ctx the parse tree
	 */
	void enterQuasiSymbolExpr(MicroSchemeParser.QuasiSymbolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#quasiSymbolExpr}.
	 * @param ctx the parse tree
	 */
	void exitQuasiSymbolExpr(MicroSchemeParser.QuasiSymbolExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#quasiSymbolDatum}.
	 * @param ctx the parse tree
	 */
	void enterQuasiSymbolDatum(MicroSchemeParser.QuasiSymbolDatumContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#quasiSymbolDatum}.
	 * @param ctx the parse tree
	 */
	void exitQuasiSymbolDatum(MicroSchemeParser.QuasiSymbolDatumContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#quasiSymbolDatumRep}.
	 * @param ctx the parse tree
	 */
	void enterQuasiSymbolDatumRep(MicroSchemeParser.QuasiSymbolDatumRepContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#quasiSymbolDatumRep}.
	 * @param ctx the parse tree
	 */
	void exitQuasiSymbolDatumRep(MicroSchemeParser.QuasiSymbolDatumRepContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MicroSchemeParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MicroSchemeParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link MicroSchemeParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(MicroSchemeParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MicroSchemeParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(MicroSchemeParser.VariableContext ctx);
}