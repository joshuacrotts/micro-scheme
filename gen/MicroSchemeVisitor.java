// Generated from C:/Users/Joshua/Desktop/Files/Java/MicroScheme/src/main/antlr4/com/joshuacrotts/microscheme\MicroScheme.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MicroSchemeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MicroSchemeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#microScheme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMicroScheme(MicroSchemeParser.MicroSchemeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(MicroSchemeParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#variableBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableBody(MicroSchemeParser.VariableBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDeclaration(MicroSchemeParser.ProcedureDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#procedureParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureParameters(MicroSchemeParser.ProcedureParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#procedureBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureBody(MicroSchemeParser.ProcedureBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#declExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclExpr(MicroSchemeParser.DeclExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MicroSchemeParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#beginExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginExpr(MicroSchemeParser.BeginExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#booleanExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpr(MicroSchemeParser.BooleanExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#evalExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvalExpr(MicroSchemeParser.EvalExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#applyExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyExpr(MicroSchemeParser.ApplyExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#whenExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenExpr(MicroSchemeParser.WhenExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#whenCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenCond(MicroSchemeParser.WhenCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#unlessExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlessExpr(MicroSchemeParser.UnlessExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#unlessCond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlessCond(MicroSchemeParser.UnlessCondContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#setExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetExpr(MicroSchemeParser.SetExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#setListExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetListExpr(MicroSchemeParser.SetListExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#letExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExpr(MicroSchemeParser.LetExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#letStarExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetStarExpr(MicroSchemeParser.LetStarExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#letRecExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetRecExpr(MicroSchemeParser.LetRecExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#letParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetParameters(MicroSchemeParser.LetParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#letBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetBody(MicroSchemeParser.LetBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#lambdaExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpr(MicroSchemeParser.LambdaExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#lambdaParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaParameters(MicroSchemeParser.LambdaParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#lambdaBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaBody(MicroSchemeParser.LambdaBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#doExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoExpr(MicroSchemeParser.DoExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#doDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoDecl(MicroSchemeParser.DoDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#doTest}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoTest(MicroSchemeParser.DoTestContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#doTrueExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoTrueExpr(MicroSchemeParser.DoTrueExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#doBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoBody(MicroSchemeParser.DoBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#condExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondExpr(MicroSchemeParser.CondExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#condForm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondForm(MicroSchemeParser.CondFormContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#ifExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr(MicroSchemeParser.IfExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#applicationExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplicationExpr(MicroSchemeParser.ApplicationExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#applicationArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplicationArgs(MicroSchemeParser.ApplicationArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#symbolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolExpr(MicroSchemeParser.SymbolExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#symbolDatum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolDatum(MicroSchemeParser.SymbolDatumContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#symbolDatumRep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolDatumRep(MicroSchemeParser.SymbolDatumRepContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#quasiSymbolExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuasiSymbolExpr(MicroSchemeParser.QuasiSymbolExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#quasiSymbolDatum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuasiSymbolDatum(MicroSchemeParser.QuasiSymbolDatumContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#quasiSymbolDatumRep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuasiSymbolDatumRep(MicroSchemeParser.QuasiSymbolDatumRepContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MicroSchemeParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link MicroSchemeParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(MicroSchemeParser.VariableContext ctx);
}