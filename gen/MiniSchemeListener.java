// Generated from C:/Users/Joshua/Desktop/Files/Java/MiniScheme/src/main/antlr4/com/joshuacrotts/minischeme\MiniScheme.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MiniSchemeParser}.
 */
public interface MiniSchemeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#miniScheme}.
	 * @param ctx the parse tree
	 */
	void enterMiniScheme(MiniSchemeParser.MiniSchemeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#miniScheme}.
	 * @param ctx the parse tree
	 */
	void exitMiniScheme(MiniSchemeParser.MiniSchemeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(MiniSchemeParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDecl}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(MiniSchemeParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code procDecl}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterProcDecl(MiniSchemeParser.ProcDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code procDecl}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitProcDecl(MiniSchemeParser.ProcDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDeclRead}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclRead(MiniSchemeParser.VarDeclReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDeclRead}
	 * labeled alternative in {@link MiniSchemeParser#decl}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclRead(MiniSchemeParser.VarDeclReadContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(MiniSchemeParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(MiniSchemeParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprCons}.
	 * @param ctx the parse tree
	 */
	void enterExprCons(MiniSchemeParser.ExprConsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprCons}.
	 * @param ctx the parse tree
	 */
	void exitExprCons(MiniSchemeParser.ExprConsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprSet}.
	 * @param ctx the parse tree
	 */
	void enterExprSet(MiniSchemeParser.ExprSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprSet}.
	 * @param ctx the parse tree
	 */
	void exitExprSet(MiniSchemeParser.ExprSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprSetRead}.
	 * @param ctx the parse tree
	 */
	void enterExprSetRead(MiniSchemeParser.ExprSetReadContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprSetRead}.
	 * @param ctx the parse tree
	 */
	void exitExprSetRead(MiniSchemeParser.ExprSetReadContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprOp}.
	 * @param ctx the parse tree
	 */
	void enterExprOp(MiniSchemeParser.ExprOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprOp}.
	 * @param ctx the parse tree
	 */
	void exitExprOp(MiniSchemeParser.ExprOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprVector}.
	 * @param ctx the parse tree
	 */
	void enterExprVector(MiniSchemeParser.ExprVectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprVector}.
	 * @param ctx the parse tree
	 */
	void exitExprVector(MiniSchemeParser.ExprVectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(MiniSchemeParser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(MiniSchemeParser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprCall}.
	 * @param ctx the parse tree
	 */
	void enterExprCall(MiniSchemeParser.ExprCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprCall}.
	 * @param ctx the parse tree
	 */
	void exitExprCall(MiniSchemeParser.ExprCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprLambdaDecl}.
	 * @param ctx the parse tree
	 */
	void enterExprLambdaDecl(MiniSchemeParser.ExprLambdaDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprLambdaDecl}.
	 * @param ctx the parse tree
	 */
	void exitExprLambdaDecl(MiniSchemeParser.ExprLambdaDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprLambdaDeclCall}.
	 * @param ctx the parse tree
	 */
	void enterExprLambdaDeclCall(MiniSchemeParser.ExprLambdaDeclCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprLambdaDeclCall}.
	 * @param ctx the parse tree
	 */
	void exitExprLambdaDeclCall(MiniSchemeParser.ExprLambdaDeclCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprIf}.
	 * @param ctx the parse tree
	 */
	void enterExprIf(MiniSchemeParser.ExprIfContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprIf}.
	 * @param ctx the parse tree
	 */
	void exitExprIf(MiniSchemeParser.ExprIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprCond}.
	 * @param ctx the parse tree
	 */
	void enterExprCond(MiniSchemeParser.ExprCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprCond}.
	 * @param ctx the parse tree
	 */
	void exitExprCond(MiniSchemeParser.ExprCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprDo}.
	 * @param ctx the parse tree
	 */
	void enterExprDo(MiniSchemeParser.ExprDoContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprDo}.
	 * @param ctx the parse tree
	 */
	void exitExprDo(MiniSchemeParser.ExprDoContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprLetDecl}.
	 * @param ctx the parse tree
	 */
	void enterExprLetDecl(MiniSchemeParser.ExprLetDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprLetDecl}.
	 * @param ctx the parse tree
	 */
	void exitExprLetDecl(MiniSchemeParser.ExprLetDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprLetNamed}.
	 * @param ctx the parse tree
	 */
	void enterExprLetNamed(MiniSchemeParser.ExprLetNamedContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprLetNamed}.
	 * @param ctx the parse tree
	 */
	void exitExprLetNamed(MiniSchemeParser.ExprLetNamedContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprSymbol}.
	 * @param ctx the parse tree
	 */
	void enterExprSymbol(MiniSchemeParser.ExprSymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprSymbol}.
	 * @param ctx the parse tree
	 */
	void exitExprSymbol(MiniSchemeParser.ExprSymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprSymbolComponent}.
	 * @param ctx the parse tree
	 */
	void enterExprSymbolComponent(MiniSchemeParser.ExprSymbolComponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprSymbolComponent}.
	 * @param ctx the parse tree
	 */
	void exitExprSymbolComponent(MiniSchemeParser.ExprSymbolComponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprTerm}.
	 * @param ctx the parse tree
	 */
	void enterExprTerm(MiniSchemeParser.ExprTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprTerm}.
	 * @param ctx the parse tree
	 */
	void exitExprTerm(MiniSchemeParser.ExprTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#exprBegin}.
	 * @param ctx the parse tree
	 */
	void enterExprBegin(MiniSchemeParser.ExprBeginContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#exprBegin}.
	 * @param ctx the parse tree
	 */
	void exitExprBegin(MiniSchemeParser.ExprBeginContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#procParams}.
	 * @param ctx the parse tree
	 */
	void enterProcParams(MiniSchemeParser.ProcParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#procParams}.
	 * @param ctx the parse tree
	 */
	void exitProcParams(MiniSchemeParser.ProcParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#procBody}.
	 * @param ctx the parse tree
	 */
	void enterProcBody(MiniSchemeParser.ProcBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#procBody}.
	 * @param ctx the parse tree
	 */
	void exitProcBody(MiniSchemeParser.ProcBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(MiniSchemeParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(MiniSchemeParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#lambdaParams}.
	 * @param ctx the parse tree
	 */
	void enterLambdaParams(MiniSchemeParser.LambdaParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#lambdaParams}.
	 * @param ctx the parse tree
	 */
	void exitLambdaParams(MiniSchemeParser.LambdaParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#lambdaBody}.
	 * @param ctx the parse tree
	 */
	void enterLambdaBody(MiniSchemeParser.LambdaBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#lambdaBody}.
	 * @param ctx the parse tree
	 */
	void exitLambdaBody(MiniSchemeParser.LambdaBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#lambdaArgs}.
	 * @param ctx the parse tree
	 */
	void enterLambdaArgs(MiniSchemeParser.LambdaArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#lambdaArgs}.
	 * @param ctx the parse tree
	 */
	void exitLambdaArgs(MiniSchemeParser.LambdaArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#letDecl}.
	 * @param ctx the parse tree
	 */
	void enterLetDecl(MiniSchemeParser.LetDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#letDecl}.
	 * @param ctx the parse tree
	 */
	void exitLetDecl(MiniSchemeParser.LetDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#letBody}.
	 * @param ctx the parse tree
	 */
	void enterLetBody(MiniSchemeParser.LetBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#letBody}.
	 * @param ctx the parse tree
	 */
	void exitLetBody(MiniSchemeParser.LetBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#doDecl}.
	 * @param ctx the parse tree
	 */
	void enterDoDecl(MiniSchemeParser.DoDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#doDecl}.
	 * @param ctx the parse tree
	 */
	void exitDoDecl(MiniSchemeParser.DoDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#doStepDecl}.
	 * @param ctx the parse tree
	 */
	void enterDoStepDecl(MiniSchemeParser.DoStepDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#doStepDecl}.
	 * @param ctx the parse tree
	 */
	void exitDoStepDecl(MiniSchemeParser.DoStepDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#doTestDecl}.
	 * @param ctx the parse tree
	 */
	void enterDoTestDecl(MiniSchemeParser.DoTestDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#doTestDecl}.
	 * @param ctx the parse tree
	 */
	void exitDoTestDecl(MiniSchemeParser.DoTestDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#doTrueExpr}.
	 * @param ctx the parse tree
	 */
	void enterDoTrueExpr(MiniSchemeParser.DoTrueExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#doTrueExpr}.
	 * @param ctx the parse tree
	 */
	void exitDoTrueExpr(MiniSchemeParser.DoTrueExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#doBody}.
	 * @param ctx the parse tree
	 */
	void enterDoBody(MiniSchemeParser.DoBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#doBody}.
	 * @param ctx the parse tree
	 */
	void exitDoBody(MiniSchemeParser.DoBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#seq}.
	 * @param ctx the parse tree
	 */
	void enterSeq(MiniSchemeParser.SeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#seq}.
	 * @param ctx the parse tree
	 */
	void exitSeq(MiniSchemeParser.SeqContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#condCond}.
	 * @param ctx the parse tree
	 */
	void enterCondCond(MiniSchemeParser.CondCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#condCond}.
	 * @param ctx the parse tree
	 */
	void exitCondCond(MiniSchemeParser.CondCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#condBody}.
	 * @param ctx the parse tree
	 */
	void enterCondBody(MiniSchemeParser.CondBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#condBody}.
	 * @param ctx the parse tree
	 */
	void exitCondBody(MiniSchemeParser.CondBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#ifCond}.
	 * @param ctx the parse tree
	 */
	void enterIfCond(MiniSchemeParser.IfCondContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#ifCond}.
	 * @param ctx the parse tree
	 */
	void exitIfCond(MiniSchemeParser.IfCondContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#ifBody}.
	 * @param ctx the parse tree
	 */
	void enterIfBody(MiniSchemeParser.IfBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#ifBody}.
	 * @param ctx the parse tree
	 */
	void exitIfBody(MiniSchemeParser.IfBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#ifElse}.
	 * @param ctx the parse tree
	 */
	void enterIfElse(MiniSchemeParser.IfElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#ifElse}.
	 * @param ctx the parse tree
	 */
	void exitIfElse(MiniSchemeParser.IfElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#op}.
	 * @param ctx the parse tree
	 */
	void enterOp(MiniSchemeParser.OpContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#op}.
	 * @param ctx the parse tree
	 */
	void exitOp(MiniSchemeParser.OpContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#unaryop}.
	 * @param ctx the parse tree
	 */
	void enterUnaryop(MiniSchemeParser.UnaryopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#unaryop}.
	 * @param ctx the parse tree
	 */
	void exitUnaryop(MiniSchemeParser.UnaryopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#binaryop}.
	 * @param ctx the parse tree
	 */
	void enterBinaryop(MiniSchemeParser.BinaryopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#binaryop}.
	 * @param ctx the parse tree
	 */
	void exitBinaryop(MiniSchemeParser.BinaryopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#ternaryop}.
	 * @param ctx the parse tree
	 */
	void enterTernaryop(MiniSchemeParser.TernaryopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#ternaryop}.
	 * @param ctx the parse tree
	 */
	void exitTernaryop(MiniSchemeParser.TernaryopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#naryop}.
	 * @param ctx the parse tree
	 */
	void enterNaryop(MiniSchemeParser.NaryopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#naryop}.
	 * @param ctx the parse tree
	 */
	void exitNaryop(MiniSchemeParser.NaryopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#setop}.
	 * @param ctx the parse tree
	 */
	void enterSetop(MiniSchemeParser.SetopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#setop}.
	 * @param ctx the parse tree
	 */
	void exitSetop(MiniSchemeParser.SetopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#readop}.
	 * @param ctx the parse tree
	 */
	void enterReadop(MiniSchemeParser.ReadopContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#readop}.
	 * @param ctx the parse tree
	 */
	void exitReadop(MiniSchemeParser.ReadopContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(MiniSchemeParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(MiniSchemeParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(MiniSchemeParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(MiniSchemeParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MiniSchemeParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MiniSchemeParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MiniSchemeParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MiniSchemeParser.ConstantContext ctx);
}